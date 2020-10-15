package io.github.harvies.charon.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Cleanup;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;

/**
 * @author harvies
 */
@Slf4j
public class ALiYunOSSProvider implements OSSProvider {

    private volatile OSS ossClient;

    private final Object lock = new Object();
    /**
     * 配置
     */
    private ALiYunOSSProperties properties;

    public ALiYunOSSProvider(ALiYunOSSProperties aLiYunOSSProperties) {
        this.properties = aLiYunOSSProperties;
    }

    @Override
    @SneakyThrows
    public FileDTO upload(@NonNull byte[] bytes, @NonNull String fileName) {
        log.info("begin upload,fileName:[{}]", fileName);
        if (ossClient == null) {
            synchronized (lock) {
                if (ossClient == null) {
                    ossClient = new OSSClientBuilder().build(properties.getEndpoint(), properties.getAccessKeyId(), properties.getAccessKeySecret());
                }
            }
        }
        String path = Utils.getPath(fileName);
        @Cleanup ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ossClient.putObject(properties.getBucketName(), path, byteArrayInputStream);
        String url = "https://" + properties.getBucketName() + "." + properties.getEndpoint() + "/" + path;
        log.info("upload success, url:[{}]", url);
        return new FileDTO(url).setCdnUrl(url);
    }
}
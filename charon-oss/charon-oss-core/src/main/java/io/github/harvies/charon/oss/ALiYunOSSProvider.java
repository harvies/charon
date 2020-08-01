package io.github.harvies.charon.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;

/**
 * @author harvies
 */
@Slf4j
public class ALiYunOSSProvider implements OSSProvider {
    /**
     * 配置
     */
    private ALiYunOSSProperties properties;

    public ALiYunOSSProvider(ALiYunOSSProperties aLiYunOSSProperties) {
        this.properties = aLiYunOSSProperties;
    }

    @Override
    public FileDTO upload(@NonNull byte[] bytes, @NonNull String fileName) {
        log.info("begin upload,fileName:[{}]", fileName);
        OSS ossClient = new OSSClientBuilder().build(properties.getEndpoint(), properties.getAccessKeyId(), properties.getAccessKeySecret());
        String path = Utils.getPath(fileName);
        try {
            ossClient.putObject(properties.getBucketName(), path, new ByteArrayInputStream(bytes));
        } finally {
            ossClient.shutdown();
        }
        String url = "https://" + properties.getBucketName() + "." + properties.getEndpoint() + "/" + path;
        log.info("upload success, url:[{}]", url);
        return new FileDTO(url).setCdnUrl(url);
    }
}
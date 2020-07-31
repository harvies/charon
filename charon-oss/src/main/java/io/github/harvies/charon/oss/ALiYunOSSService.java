package io.github.harvies.charon.oss;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author harvies
 */
@Slf4j
public class ALiYunOSSService implements OSSService {
    /**
     * 配置
     */
    private ALiYunOSSProperties aLiYunOSSProperties;

    public ALiYunOSSService(ALiYunOSSProperties aLiYunOSSProperties) {
        this.aLiYunOSSProperties = aLiYunOSSProperties;
    }

    @SneakyThrows
    @Override
    public FileDTO upload(byte[] bytes, String fileName) {
        log.info("fileName:[{}]", fileName);
        return new FileDTO("null");
    }
}
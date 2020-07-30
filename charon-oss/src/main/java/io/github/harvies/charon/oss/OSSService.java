package io.github.harvies.charon.oss;

/**
 * OSS服务
 *
 * @author harvies
 */
public interface OSSService {
    /**
     * 上传文件
     *
     * @param bytes    文件字节数组
     * @param fileName 文件名
     * @return 结果
     */
    FileDTO upload(byte[] bytes, String fileName);
}

package io.github.harvies.charon.oss;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
     * @return 文件信息
     */
    FileDTO upload(byte[] bytes, String fileName);


    /**
     * 上传文件
     *
     * @param fileUrl  文件URL地址
     * @param fileName 文件名
     * @return 文件信息
     */
    default FileDTO upload(String fileUrl, String fileName) throws IOException {
        byte[] bytes = IOUtils.toByteArray(new URL(fileUrl));
        return upload(bytes, fileName);
    }


}

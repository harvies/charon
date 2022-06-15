package io.github.harvies.charon.picture.compress;


import java.io.IOException;

public interface FileCompress {

    /**
     * 注册当前文件处理器
     */
    void register();

    /**
     * 返回文件类型
     */
    String getMimeType();

    /**
     * 填充宽高信息
     */
    void fillWidthHeight(FileContext fileContext) throws IOException;

    /**
     * 压缩前处理逻辑
     */
    void compressBefore(FileContext fileContext) throws Exception;

    /**
     * 压缩
     */
    void compress(FileContext fileContext) throws Exception;

    /**
     * 压缩后处理逻辑
     */
    void compressAfter(FileContext fileContext) throws Exception;
}

package io.github.harvies.charon.picture.compress;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.File;

@Data
public class BaseFileContext {

    /**
     * 文件全名
     */
    @NotNull
    private String fileFullName;
    /**
     * 循环压缩
     */
    private boolean recursion = false;

    /**
     * 初次不压缩阈值 kb
     */
    private long initialNonCompressionThreshold = 512;
    /**
     * 压缩后最大大小(kb)
     */
    private long maxSize = 2048;

    /**
     * 压缩质量
     */
    private float outputQuality = 0.8f;

    /**
     * 最大宽度(高于，则自动等比压缩到此宽度)
     */
    private int maxWidth = 1440;


    /**
     * 最大高度，超过该高度抛异常
     */
    private int maxHeight = 10240;


    ///////////////自动填充字段///////////////

    /**
     * 压缩后的文件
     */
    private File outputFile;

    /**
     * 压缩后文件大小(byte)
     */
    private long outputFileSize;
    /**
     * 文件类型
     */
    private String mimeType;
    /**
     * 宽
     */
    private Integer width;
    /**
     * 高
     */
    private Integer height;
    /**
     * 宽度压缩
     */
    private boolean widthCompress = false;

    /**
     * 文件名
     */
    private String name;
    /**
     * 文件后缀
     */
    private String suffix;

    /**
     * 工作空间（存储临时数据）
     */
    private String workSpace;
}

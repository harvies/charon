package io.github.harvies.charon.picture.compress;

import io.github.harvies.charon.picture.compress.strategy.BMPCompress;
import io.github.harvies.charon.picture.compress.strategy.GIFCompress;
import io.github.harvies.charon.picture.compress.strategy.JPGCompress;
import io.github.harvies.charon.picture.compress.strategy.PNGCompress;
import lombok.extern.slf4j.Slf4j;
import net.sf.jmimemagic.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
public class FileCompressUtils {
    static {
        new JPGCompress().register();
        new PNGCompress().register();
        new GIFCompress().register();
        new BMPCompress().register();
    }

    /**
     * 压缩完需执行clean方法，清理生成的文件
     *
     * @throws Exception
     */
    public static FileContext compress(InputStreamContext inputStreamContext) throws Exception {
        File tmpFile = new File(FileConfig.TMP_PATH + "/" + UUID.randomUUID());
        try {
            FileUtils.copyToFile(inputStreamContext.getInputStream(), tmpFile);
            FileContext fileContext = FileCompressConvert.INSTANCE.convert(inputStreamContext);
            fileContext.setFile(tmpFile);
            compress(fileContext);
            return fileContext;
        } finally {
            if (tmpFile.exists()) {
                FileUtils.delete(tmpFile);
            }
        }
    }

    public static void compress(FileContext fileContext) throws Exception {
        setWorkSpace(fileContext);
        fillNameAndSuffix(fileContext);
        fillMimeType(fileContext);

        FileCompress abstractFileCompress = FileCompressContext.get(fileContext.getMimeType());
        if (abstractFileCompress == null) {
            log.info("格式:[{}] 处理器 未实现", fileContext.getSuffix());
            fillOriginFileToOutputFile(fileContext);
            return;
        }
        abstractFileCompress.fillWidthHeight(fileContext);

        //如果图片大小低于多少，则不压缩
        if (FileCompressUtils.getSize(fileContext.getFile()) <= fileContext.getInitialNonCompressionThreshold() * 1024) {
            fillOriginFileToOutputFile(fileContext);
            return;
        }
        calculateOutputQuality(fileContext);

        try {
            log.info("开始执行图片压缩 fileContext:[{}]", fileContext);
            abstractFileCompress.compress(fileContext);
        } finally {
            if (!FileConfig.debug) {
                clean(fileContext);
            }
            log.info("结束执行图片压缩 fileContext:[{}]", fileContext);
        }

    }


    /**
     * 设置输出文件为原文件
     */
    public static void fillOriginFileToOutputFile(FileContext fileContext) throws IOException {
        File outputFile = new File(fileContext.getWorkSpace() + "/" + fileContext.getName() + "_" + "origin" + "." + fileContext.getSuffix());
        FileUtils.copyFile(fileContext.getFile(), outputFile);
        fileContext.setOutputFile(outputFile);
    }


    /**
     * 计算最优压缩比例
     *
     * @param fileContext
     */
    private static void calculateOutputQuality(FileContext fileContext) {
        Integer width = fileContext.getWidth();
        if (width > fileContext.getMaxWidth()) {
            float outputQuality = BigDecimal.valueOf(fileContext.getMaxWidth()).divide(BigDecimal.valueOf(fileContext.getWidth()), 3, RoundingMode.HALF_UP).floatValue();
            fileContext.setWidthCompress(true);
            fileContext.setOutputQuality(outputQuality);
        }
    }

    public static void clean(BaseFileContext inputStreamContext) throws IOException {
        if (inputStreamContext.getOutputFile() == null) {
            return;
        }
        if (inputStreamContext.getOutputFile().exists()) {
            FileUtils.delete(inputStreamContext.getOutputFile());
        }
        File parentFile = inputStreamContext.getOutputFile().getParentFile();
        if (parentFile.exists()) {
            FileUtils.deleteDirectory(parentFile);
        }
    }


    private static void clean(FileContext fileContext) throws IOException {
        if (fileContext == null) {
            return;
        }
        Iterator<File> fileIterator = org.apache.commons.io.FileUtils.iterateFiles(new File(fileContext.getWorkSpace()), null, true);
        while (fileIterator.hasNext()) {
            File next = fileIterator.next();
            if (!StringUtils.equals(next.getAbsolutePath(), fileContext.getOutputFile().getAbsolutePath())) {
                org.apache.commons.io.FileUtils.delete(next);
            }
        }
    }

    private static void setWorkSpace(FileContext fileContext) throws IOException {
        String workspace = FileConfig.TMP_PATH + "/" + UUID.randomUUID();
        org.apache.commons.io.FileUtils.forceMkdir(new File(workspace));
        fileContext.setWorkSpace(workspace);
    }

    private static void fillMimeType(FileContext fileContext) {
        StopWatch stopwatch = StopWatch.createStarted();
        try {
            MagicMatch match = Magic.getMagicMatch(fileContext.getFile(), false);
            log.info("成功获取到文件类型 mimeType:{}] fileContext:[{}]", match.getMimeType(), fileContext);
            fileContext.setMimeType(match.getMimeType());
            return;
        } catch (Exception e) {
            log.warn("获取文件格式异常  fileContext :[{}]", fileContext, e);
        }
        String lowerSuffix = fileContext.getSuffix().toLowerCase();
        if (StringUtils.equals(lowerSuffix, "jpg") || StringUtils.equals(lowerSuffix, "jpeg")) {
            fileContext.setMimeType(JPGCompress.IMAGE_JPEG);
        } else if (StringUtils.equals(lowerSuffix, "png")) {
            fileContext.setMimeType(PNGCompress.IMAGE_PNG);
        } else if (StringUtils.equals(lowerSuffix, "bmp")) {
            fileContext.setMimeType(BMPCompress.IMAGE_BMP);
        }
        log.info("fillMimeType耗时:[{}] ms  fileContext:[{}]", stopwatch.getTime(TimeUnit.MILLISECONDS), fileContext);
    }

    public static long getSize(File file) {
        return org.apache.commons.io.FileUtils.sizeOf(file);
    }

    private static void fillNameAndSuffix(FileContext fileContext) {
        String fileFullName = fileContext.getFileFullName();
        int lastIndexOf = StringUtils.lastIndexOf(fileFullName, '.');
        if (StringUtils.isBlank(fileContext.getName())) {
            String name = StringUtils.substring(fileFullName, 0, lastIndexOf);
            fileContext.setName(name);
        }
        if (lastIndexOf == -1) {
            return;
        }
        if (StringUtils.isBlank(fileContext.getSuffix())) {
            String suffix = StringUtils.substring(fileFullName, lastIndexOf + 1);
            fileContext.setSuffix(suffix);
        }
    }

}

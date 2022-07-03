package io.github.harvies.charon.picture.compress;

import io.github.harvies.charon.picture.compress.enums.ImageCompressExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
public abstract class AbstractImageCompress implements FileCompress {
    @Override
    public void fillWidthHeight(FileContext fileContext) throws IOException {
        FastImageInfo fastImageInfo = new FastImageInfo(fileContext.getFile());
        int width = fastImageInfo.getWidth();
        int height = fastImageInfo.getHeight();
        if (height > fileContext.getMaxHeight()) {
            throw new ImageCompressException(ImageCompressExceptionEnum.HEIGHT_EXCEEDS_MAXIMUM, fileContext);
        }
        fileContext.setWidth(width);
        fileContext.setHeight(height);
    }

    @Override
    public void compressBefore(FileContext fileContext) throws Exception {
    }

    @Override
    public void compressAfter(FileContext fileContext) throws Exception {
    }

    @Override
    public void register() {
        FileCompressContext.register(this);
    }

    @Override
    public void compress(FileContext fileContext) throws Exception {
        try {
            //设置最大压缩次数，减少压缩时间
            for (int i = 0; i < FileConfig.RECURSION_COMPRESS_TIME; i++) {
                if (!fileContext.isRecursion() && i == 1) {
                    return;
                }

                File outputFile = new File(fileContext.getWorkSpace() + "/" + fileContext.getName() + "_" + fileContext.getOutputQuality() + "." + fileContext.getSuffix());
                fileContext.setOutputFile(outputFile);
                compressBefore(fileContext);
                doCompress(fileContext);
                compressAfter(fileContext);
                long size = FileCompressUtils.getSize(fileContext.getOutputFile());
                fileContext.setOutputFileSize(size);
                if (size <= fileContext.getMaxSize() * 1024) {
                    return;
                }
                BigDecimal outputQuality = BigDecimal.valueOf(fileContext.getOutputQuality()).multiply(BigDecimal.valueOf(0.8)).setScale(1, RoundingMode.DOWN);
                //经过测试将10几M的图片进行压缩，压缩比例在0.4以下时会出现明显失真
                if (outputQuality.floatValue() <= 0.4) {
                    return;
                }
                fileContext.setOutputQuality(outputQuality.floatValue());
            }
        } catch (Throwable e) {
            log.info("图片压缩异常 fileContext:[{}]", fileContext, e);
            File outputFile = new File(fileContext.getWorkSpace() + "/" + fileContext.getName() + "_" + "origin" + "." + fileContext.getSuffix());
            FileUtils.copyFile(fileContext.getFile(), outputFile);
            fileContext.setOutputFile(outputFile);
        }

    }

    private void doCompress(FileContext fileContext) throws IOException {
        log.info("doCompress fileContext:[{}]", fileContext);
        //在原图的基础上压缩效果最好
        compress0(fileContext);
    }

    public abstract void compress0(FileContext fileContext) throws IOException;

}

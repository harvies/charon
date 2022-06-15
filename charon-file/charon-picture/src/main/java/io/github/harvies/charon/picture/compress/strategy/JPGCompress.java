package io.github.harvies.charon.picture.compress.strategy;

import io.github.harvies.charon.picture.compress.AbstractImageCompress;
import io.github.harvies.charon.picture.compress.FileCompress;
import io.github.harvies.charon.picture.compress.FileContext;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;

@Slf4j
public class JPGCompress extends AbstractImageCompress implements FileCompress {

    public static final String IMAGE_JPEG = "image/jpeg";

    @Override
    public void compressBefore(FileContext fileContext) throws Exception {
        super.compressBefore(fileContext);
    }

    @Override
    public String getMimeType() {
        return IMAGE_JPEG;
    }

    @Override
    public void compress0(FileContext fileContext) throws IOException {
        if (fileContext.isWidthCompress()) {
            //如果已经压缩过一次则使用此次比例缩小方式进行压缩
            Thumbnails.of(fileContext.getFile()).scale(fileContext.getOutputQuality()).outputQuality(0.8).toFile(fileContext.getOutputFile());
        } else {
            //默认使用等尺寸压缩
            Thumbnails.of(fileContext.getFile()).scale(1f).outputQuality(fileContext.getOutputQuality()).toFile(fileContext.getOutputFile());
        }
    }
}

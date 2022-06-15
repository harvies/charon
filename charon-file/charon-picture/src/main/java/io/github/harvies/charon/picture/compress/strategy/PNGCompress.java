package io.github.harvies.charon.picture.compress.strategy;


import com.biejieshi.compress.PngCompressUtils;
import io.github.harvies.charon.picture.compress.AbstractImageCompress;
import io.github.harvies.charon.picture.compress.FileCompress;
import io.github.harvies.charon.picture.compress.FileContext;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
public class PNGCompress extends AbstractImageCompress implements FileCompress {

    public static final String IMAGE_PNG = "image/png";

    @Override
    public void compressBefore(FileContext fileContext) throws Exception {
        if (fileContext.isWidthCompress()) {
            int desWidth = new BigDecimal(fileContext.getWidth()).multiply(
                    BigDecimal.valueOf(fileContext.getOutputQuality())).intValue();
            int desHeight = new BigDecimal(fileContext.getHeight()).multiply(
                    BigDecimal.valueOf(fileContext.getOutputQuality())).intValue();
            Thumbnails.of(fileContext.getFile()).size(desWidth, desHeight).imageType(BufferedImage.TYPE_INT_ARGB).outputQuality(0.8).toFile(fileContext.getOutputFile());
            fileContext.setFile(fileContext.getOutputFile());
        }
        super.compressBefore(fileContext);
    }

    @Override
    public String getMimeType() {
        return IMAGE_PNG;
    }

    @Override
    public void compress0(FileContext fileContext) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(fileContext.getFile());
        @Cleanup FileOutputStream fileOutputStream = new FileOutputStream(fileContext.getOutputFile());
        // TODO: 2022/6/15 优化
        if (fileContext.isWidthCompress()) {
            //如果已经压缩过一次则使用此次比例缩小方式进行压缩
            ImageIO.write(PngCompressUtils.compressPng(bufferedImage, 80), "png", fileOutputStream);
        } else {
            //默认使用等尺寸压缩
            int quality = BigDecimal.valueOf(fileContext.getOutputQuality()).multiply(BigDecimal.valueOf(100)).setScale(0, RoundingMode.UP).intValue();
            ImageIO.write(PngCompressUtils.compressPng(bufferedImage, quality), "png", fileOutputStream);
        }
    }

}

package com.biejieshi.compress;

import org.pngquant.PngQuant;

import java.awt.image.BufferedImage;
import java.util.Optional;

/**
 * Png图片压缩工具类
 *
 * @author lcry
 */
public class PngCompressUtils {
    /**
     * 压缩图片bufferedImage，若无法达到压缩比例，原样返回
     *
     * @param bufferedImage 待压缩的bufferedImage
     * @param quality 压缩质量
     * @return 压缩后的bufferedImage
     */
    public static BufferedImage compressPng(BufferedImage bufferedImage, int quality) {
        PngQuant pngQuant = new PngQuant();
        //压缩质量质量在 0-100 范围内。如果给定数量的颜色无法达到最低质量，则量化将失败
        //如果可能，使用质量 80。如果质量低于 65，则放弃。
        pngQuant.setQuality(40, quality);
        return Optional.ofNullable(pngQuant.getRemapped(bufferedImage)).orElse(bufferedImage);
    }

    /**
     * 压缩图片bufferedImage，若无法达到压缩比例，原样返回
     *
     * @param bufferedImage 待压缩的bufferedImage
     * @return 压缩后的bufferedImage
     */
    public static BufferedImage compressPng(BufferedImage bufferedImage) {
        return compressPng(bufferedImage, 80);
    }
}
package io.github.harvies.charon.picture;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;

/**
 * Created by hdwang on 2018/12/13.
 */
@Slf4j
public class PicUtils {

    /**
     * 根据指定大小和指定精度压缩图片
     *
     * @param srcByte      源图片字节数组
     * @param desFileSize  指定图片大小，单位kb
     * @param accuracy     精度，递归压缩的比率，建议小于0.9
     * @param desMaxWidth  目标最大宽度
     * @param desMaxHeight 目标最大高度
     * @return 目标文件字节数组
     */
    public static byte[] compressPicForScale(byte[] srcByte,
                                             long desFileSize, double accuracy, int desMaxWidth, int desMaxHeight) throws IOException {
        if (srcByte == null || srcByte.length == 0) {
            return null;
        }
        //获取图片信息
        @Cleanup ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(srcByte);
        BufferedImage bim = ImageIO.read(byteArrayInputStream);
        int srcWidth = bim.getWidth();
        int srcHeight = bim.getHeight();

        //先转换成jpg
        @Cleanup ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(srcByte);
        Thumbnails.Builder builder = Thumbnails.of(byteArrayInputStream2).outputFormat("jpg");

        // 指定大小（宽或高超出会才会被缩放）
        if (srcWidth > desMaxWidth || srcHeight > desMaxHeight) {
            builder.size(desMaxWidth, desMaxHeight);
        } else {
            //宽高均小，指定原大小
            builder.size(srcWidth, srcHeight);
        }

        // 写入到内存
        @Cleanup ByteArrayOutputStream baos = new ByteArrayOutputStream(); //字节输出流（写入到内存）
        builder.toOutputStream(baos);

        // 递归压缩，直到目标文件大小小于desFileSize
        byte[] bytes = compressPicCycle(baos.toByteArray(), desFileSize, accuracy);

        log.info("图片压缩完成!源图片大小:[{} kb],目标图片大小[{} kb]", srcByte.length / 1024, bytes.length / 1024);
        return bytes;
    }

    private static byte[] compressPicCycle(byte[] bytes, long desFileSize, double accuracy) throws IOException {
        // File srcFileJPG = new File(desPath);
        long srcFileSizeJPG = bytes.length;
        // 2、判断大小，如果小于500kb，不压缩；如果大于等于500kb，压缩
        if (srcFileSizeJPG <= desFileSize * 1024) {
            return bytes;
        }
        // 计算宽高
        @Cleanup ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        BufferedImage bim = ImageIO.read(byteArrayInputStream);
        int srcWdith = bim.getWidth();
        int srcHeigth = bim.getHeight();
        int desWidth = new BigDecimal(srcWdith).multiply(
                new BigDecimal(accuracy)).intValue();
        int desHeight = new BigDecimal(srcHeigth).multiply(
                new BigDecimal(accuracy)).intValue();
        @Cleanup ByteArrayOutputStream baos = new ByteArrayOutputStream(); //字节输出流（写入到内存）
        @Cleanup ByteArrayInputStream byteArrayInputStream1 = new ByteArrayInputStream(bytes);
        Thumbnails.of(byteArrayInputStream1).size(desWidth, desHeight).outputQuality(accuracy).toOutputStream(baos);
        return compressPicCycle(baos.toByteArray(), desFileSize, accuracy);
    }
}
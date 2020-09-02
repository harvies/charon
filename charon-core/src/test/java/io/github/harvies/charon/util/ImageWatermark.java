package io.github.harvies.charon.util;

import lombok.Builder;
import lombok.Data;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/*
 * 给图片添加单个图片水印、可设置水印图片旋转角度
 * */
public class ImageWatermark {

    /**
     * icon 水印图片路径（如：F:/images/icon.png）
     * source 没有加水印的图片路径（如：F:/images/6.jpg）
     * output 加水印后的图片路径（如：F:/images/）
     * imageName 图片名称（如：11111）
     * imageType 图片类型（如：jpg）
     * degree 水印图片旋转角度，为null表示不旋转
     */
    @Builder
    @Data
    static class Param {
        private String icon;
        private String source;
        private String output;
        private String imageName;
        private String imageType;
        private Integer degree;
    }

    public static Boolean markImageBySingleIcon(Param param) {

        try {
            File file = new File(param.getSource());
            File ficon = new File(param.getIcon());

            if (!file.isFile()) {
                return false;
            }

            //将icon加载到内存中
            Image ic = ImageIO.read(ficon);
            //icon宽度高度
            int icWeight = ic.getWidth(null);
            int icHeight = ic.getHeight(null);
            //将源图片读到内存中
            Image img = ImageIO.read(file);

            //图片宽
            int width = img.getWidth(null);
            //图片高
            int height = img.getHeight(null);

            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            //创建一个指定 BufferedImage 的 Graphics2D 对象
            Graphics2D g = bi.createGraphics();

            //x,y轴默认是从0坐标开始
            int x = (width - icWeight) / 2;
            int y = (height - icHeight) / 2;

            //设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            //呈现一个图像，在绘制前进行从图像空间到用户空间的转换
            g.drawImage(img.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

            if (null != param.getDegree()) {
                //设置水印旋转
                g.rotate(Math.toRadians(param.getDegree()), (double) bi.getWidth() / 2, (double) bi.getHeight() / 2);
            }
            //水印图象的路径 水印一般为gif或者png的，这样可设置透明度
            ImageIcon imgIcon = new ImageIcon(param.getIcon());
            //得到Image对象。
            Image con = imgIcon.getImage();
            //透明度，最小值为0，最大值为1
            float clarity = 0.6f;
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, clarity));
            //表示水印图片的坐标位置(x,y)
            //g.drawImage(con, 300, 220, null);
            g.drawImage(con, x, y, null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            g.dispose();
            File sf = new File(param.getOutput(), param.getImageName() + "." + param.getImageType());
            ImageIO.write(bi, param.getImageType(), sf); // 保存图片

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
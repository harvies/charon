package io.github.harvies.charon.util.text;

import java.text.DecimalFormat;

/**
 * Java中使用DecimalFormat最多保留两位小数，同时不保留0
 * refer:https://blog.csdn.net/qqq2830/article/details/79879176
 *
 * @author harvies
 */
public class DecimalFormatTest {

    public static void main(String[] s) {

        DecimalFormat decimalFormat = new DecimalFormat("0.##");

        double l = 101;

        String format = decimalFormat.format(l / 100);

        System.out.println(format);

        l = 100;

        format = decimalFormat.format(l / 100);

        System.out.println(format);

        l = 110;

        format = decimalFormat.format(l / 100);

        System.out.println(format);
    }

}

package io.github.harvies.charon.util;

import org.apache.commons.text.StringEscapeUtils;

/**
 * 字符串工具类
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * unicode解码
     *
     * @param input 输入
     * @return 结果
     */
    public static String unicodeDecode(final String input) {
        return StringEscapeUtils.unescapeJava(input);
    }

    /**
     * unicode编码
     *
     * @param input 输入
     * @return 结果
     */
    public static String unicodeEncode(final String input) {
        return StringEscapeUtils.escapeJava(input);
    }

    /**
     * 格式化,输出hashcode
     *
     * @param o 入参
     * @return 结果
     */
    public static String format(Object o) {
        return o.toString() + ",identityHashCode:" + System.identityHashCode(o) + ",hashCode:" + o.hashCode();
    }

    /**
     * 打印
     *
     * @param o 入参
     */
    public static void println(Object o) {
        System.out.println(format(o));
    }
}

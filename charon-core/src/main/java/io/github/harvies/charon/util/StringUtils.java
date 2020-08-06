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

}

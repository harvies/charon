package io.github.harvies.charon.util;

public class XmlUtils {

    /**
     * 清理xml特殊字符
     */
    public static String clean(String xml) {
        if (StringUtils.isBlank(xml)) {
            return xml;
        }
        //清理头部空格
        xml = xml.replaceAll(StringUtils.substringBefore(xml, "<?xml"), "");
        //清理特殊字符
        xml = xml.replaceAll("[\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]", "");
        return xml;
    }
}

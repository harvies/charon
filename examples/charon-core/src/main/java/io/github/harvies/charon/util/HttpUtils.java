package io.github.harvies.charon.util;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpUtils {

    /**
     * @param cookies 字符串格式cookie 例如: _ga=GA1.2.1045344021.15912182139; uid=29833; email=xxx%40gmail.com; key=603440b55cd918483a914001f772af3584b0e87510f2af5; ip=9278d41d407ca5fd48072a7110c3264258; expire_in=1596806426; __cfduid=d3c92541094493a903b135410d9d1c11fb91596721714; _gid=GA1.2.1978986303.1596721718; _gat_gtag_UA_90263540_5=1; _gat=1
     * @return Map格式cookie
     */
    public static Map<String, String> strCookieToMap(String cookies) {
        ArrayList<String> cookieList = Lists.newArrayList(Splitter.on("; ").split(cookies));
        HashMap<String, String> cookieMap = Maps.newHashMap();
        for (String s : cookieList) {
            ArrayList<String> strings = Lists.newArrayList(Splitter.on("=").split(s).iterator());
            if (strings.size() == 2) {
                cookieMap.put(strings.get(0), strings.get(1));
            }
        }
        return cookieMap;
    }

    public static String getFileNameFromUrl(String urlPath) {
        // 指定文件后缀名称(有需求可以自定义，如doc等等)
        String suffixes = "jpeg|jpg|png|pdf|xlsx|xls|md|txt";
        Pattern pat = Pattern.compile("[0-9a-zA-Z_-]+[.](" + suffixes + ")");//正则判断
        Matcher mc = pat.matcher(urlPath);//条件匹配
        while (mc.find()) {
            return mc.group();
        }
        return null;
    }
}

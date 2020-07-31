package io.github.harvies.charon.oss;

import org.apache.commons.lang3.time.DateFormatUtils;

public class Utils {

    public static String getPath(String fileName) {
        String dateStr = DateFormatUtils.format(System.currentTimeMillis(), "yyyy/MM/dd/yyyymmddHHmmsssss-");
        return dateStr + fileName;
    }
}

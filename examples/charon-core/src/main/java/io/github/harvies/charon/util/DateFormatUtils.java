package io.github.harvies.charon.util;

import java.util.Date;

public class DateFormatUtils extends org.apache.commons.lang3.time.DateFormatUtils {

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static String formatToNormal(Date date) {
        return format(date, YYYY_MM_DD_HH_MM_SS);
    }
}

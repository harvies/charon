package io.github.harvies.charon.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    /**
     * 获取明天第一秒
     * @return date example: Mon Mar 08 00:00:00 CST 2021
     */
    public static Date getTomorrowFirstSecond() {
        return Date.from(LocalDate.now().plusDays(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
}

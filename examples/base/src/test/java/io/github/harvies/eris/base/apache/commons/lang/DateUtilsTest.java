package io.github.harvies.eris.base.apache.commons.lang;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.util.Date;

public class DateUtilsTest {
    @Test
    public void dateFormatUtils() {
        String format = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        System.err.println(format);
    }

    @Test
    public void dateUtils() {
        Date date = DateUtils.addMonths(new Date(),2);
        System.err.println(DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));

    }
}

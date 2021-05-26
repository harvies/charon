package io.github.harvies.charon.util;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class DateFormatUtilsTest {

    @Test
    void formatToNormal() throws ParseException {
        String dateStr = "2020-05-01 11:22:33";
        Date date = DateUtils.parseDate(dateStr, DateFormatUtils.YYYY_MM_DD_HH_MM_SS);
        assertThat(DateFormatUtils.formatToNormal(date), is(dateStr));
    }
}
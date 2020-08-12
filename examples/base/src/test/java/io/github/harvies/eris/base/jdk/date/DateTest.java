package io.github.harvies.eris.base.jdk.date;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class DateTest {

    @Test
    public void dateTimeFormatter() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDate = LocalDateTime.parse("2018-11-22 11:22:11", dateTimeFormatter);
        System.err.println("localDate:" + localDate);
        String format = dateTimeFormatter.format(LocalDateTime.now());
        System.err.println("format:" + format);
    }
}

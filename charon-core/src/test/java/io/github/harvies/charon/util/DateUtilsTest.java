package io.github.harvies.charon.util;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @Test
    void getTomorrowFirstSecond() {
        Date tomorrowFirstSecond = DateUtils.getTomorrowFirstSecond();
        System.out.println(tomorrowFirstSecond);
    }
}

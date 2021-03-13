package io.github.harvies.charon.tests.base.apache.commons.lang;


import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.text.RandomStringGenerator;

import java.util.Date;

/**
 * format后面的格式不要包含多余的数据
 */
public class DateFormatTest {
    private static final RandomStringGenerator randomNumberGenerator = new RandomStringGenerator.Builder().selectFrom(
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9').build();

    public static void main(String[] args) {

        for (int i = 0; i < 100000; i++) {
//            String yyyyMMddHHmmss = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss") + randomNumberGenerator.generate(8);
            String yyyyMMddHHmmss = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss" + randomNumberGenerator.generate(8));

            if (i % 10000 == 0) {
                System.err.println(yyyyMMddHHmmss);
            }
        }
    }
}

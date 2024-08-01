package io.github.harvies.charon.util;

import org.junit.jupiter.api.Test;

public class RegularTest {

    @Test
    public void test() {
        String aa = "小行星5";
        String str = "小行星5义神星（Astraea）[5]";
        System.out.println(StringUtils.substringBetween("小行星5义神星（Astraea）[5]", "5", "（"));
        System.out.println(StringUtils.substring(str, aa.length(), str.lastIndexOf("（")));
        System.out.println(StringUtils.substring(str, str.lastIndexOf("（") + 1, str.lastIndexOf("）")));
    }
}

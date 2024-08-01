package io.github.harvies.charon.util;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class Md5UtilsTest {

    @Test
    void encode() {
        assertThat(Md5Utils.encode("123456"), is("e10adc3949ba59abbe56e057f20f883e"));
    }
}
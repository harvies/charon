package io.github.harvies.charon.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class URLUtilsTest {

    @Test
    void encode() throws EncoderException {
        assertThat(URLUtils.encode("你好"), is("%E4%BD%A0%E5%A5%BD"));
    }

    @Test
    void decode() throws DecoderException {
        assertThat(URLUtils.decode("%E4%BD%A0%E5%A5%BD"), is("你好"));
    }
}
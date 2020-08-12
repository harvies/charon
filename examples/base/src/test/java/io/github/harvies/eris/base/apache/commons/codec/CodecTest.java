package io.github.harvies.eris.base.apache.commons.codec;

import com.google.common.net.UrlEscapers;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.net.URLCodec;
import org.junit.Test;

import java.net.URLEncoder;

public class CodecTest {
    @Test
    public void testBase64() {
        String a = "abcdefg";
        String s = Base64.encodeBase64String(a.getBytes());
        System.err.println(s);

        byte[] bytes = Base64.decodeBase64(s);
        String s2 = StringUtils.newString(bytes, "UTF-8");
        System.err.println(s2);
    }

    @Test
    public void testMd5() {
        String a = "123456";
        String s = DigestUtils.md5Hex(a);
        System.err.println(s);
    }

    @Test
    public void testURLCodec() throws Exception {
        String a = "T恤";
        URLCodec urlCodec = new URLCodec();
        String encode = urlCodec.encode(a);
        System.err.println(encode);
        String decode = urlCodec.decode(encode, "UTF-8");
        System.err.println(decode);
        String escape = UrlEscapers.urlPathSegmentEscaper().escape("T恤");
        System.err.println(escape);
        String encode1 = URLEncoder.encode("T恤", "UTF-8");
        System.err.println(encode1);
    }
}

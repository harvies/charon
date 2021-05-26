package io.github.harvies.charon.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;

/**
 * URLUtils
 */
public class URLUtils {

    public static String encode(String str) throws EncoderException {
        URLCodec urlCodec = new URLCodec();
        //UrlEscapers.urlPathSegmentEscaper().escape("");
        return urlCodec.encode(str);
    }

    public static String decode(String str) throws DecoderException {
        URLCodec urlCodec = new URLCodec();
        return urlCodec.decode(str);
    }
}

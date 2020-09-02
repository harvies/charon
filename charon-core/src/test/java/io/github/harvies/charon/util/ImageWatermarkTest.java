package io.github.harvies.charon.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageWatermarkTest {

    @Test
    void markImageBySingleIcon() {
        String icon = FileUtils.getCurrentUserHomePath() + "/Downloads/icon.png";
        String source = FileUtils.getCurrentUserHomePath() + "/Downloads/520dcdcf-932c-4d19-bfc8-b7b00d592f8f.jpeg";
        String output = FileUtils.getCurrentUserHomePath() + "/Downloads/";
        ImageWatermark.Param.ParamBuilder paramBuilder = ImageWatermark.Param.builder()
                .icon(icon)
                .source(source)
                .output(output)
                .degree(null)
                .imageName("aaa")
                .imageType("jpeg");
        ImageWatermark.markImageBySingleIcon(paramBuilder.build());
        PicUtils.compressPicForScale(output+"aaa.jpeg",output+"aaa_1.jpeg",500,0.8,999999999,99999999);
    }
}
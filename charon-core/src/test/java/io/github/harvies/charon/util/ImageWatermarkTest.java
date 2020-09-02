package io.github.harvies.charon.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageWatermarkTest {

    @Test
    void markImageBySingleIcon() {
        String icon = FileUtils.getCurrentUserHomePath() + "/Downloads/baidu.png";
        String source = FileUtils.getCurrentUserHomePath() + "/Downloads/img.jpeg";
        String output = FileUtils.getCurrentUserHomePath() + "/Downloads/";
        ImageWatermark.Param.ParamBuilder paramBuilder = ImageWatermark.Param.builder()
                .icon(icon)
                .source(source)
                .output(output)
                .degree(null)
                .imageName("aaa")
                .imageType("jpeg");
        ImageWatermark.markImageBySingleIcon(paramBuilder.build());
    }
}
package io.github.harvies.charon.picture;

import io.github.harvies.charon.util.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class ImageWatermarkTest {

    @Test
    void markImageBySingleIcon() throws IOException {
        byte[] iconByteArray = FileUtils.readFileToByteArray(new File(FileUtils.getCurrentUserHomePath() + "/Downloads/icon.png"));
        byte[] sourceByteArray = FileUtils.readFileToByteArray(new File(FileUtils.getCurrentUserHomePath() + "/Downloads/520dcdcf-932c-4d19-bfc8-b7b00d592f8f.jpeg"));
        ImageWatermark.Param.ParamBuilder paramBuilder = ImageWatermark.Param.builder()
                .icon(iconByteArray)
                .source(sourceByteArray)
                .degree(null)
                .imageType("jpeg");
        byte[] watermarkByteArray = ImageWatermark.markImageBySingleIcon(paramBuilder.build());
        byte[] compressByteArray = PicUtils.compressPicForScale(watermarkByteArray, 500, 0.8, 999999999, 99999999);
        FileUtils.writeByteArrayToFile(new File(FileUtils.getCurrentUserHomePath() + "/Downloads/" + "aaa_1.jpeg"), compressByteArray);
    }
}
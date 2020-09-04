package io.github.harvies.charon.picture;

import io.github.harvies.charon.util.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class PicUtilsTest {

    @Test
    void compressPicForScale() throws IOException {
        byte[] bytes = PicUtils.compressPicForScale(FileUtils.readFileToByteArray(new File(FileUtils.getCurrentUserHomePath() + "/Downloads/苹果自带macos mojove Mojave Night 莫哈韦沙漠晚上风景5K壁纸_彼岸图网.jpg")), 500, 0.9, 99999999, 99999999);// 图片小于1000kb
        FileUtils.writeByteArrayToFile(new File(FileUtils.getCurrentUserHomePath() + "/Downloads/aaaa.jpeg"), bytes);
    }
}
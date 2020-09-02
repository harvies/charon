package io.github.harvies.charon.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PicUtilsTest {

    @Test
    void compressPicForScale() {
        PicUtils.compressPicForScale(FileUtils.getCurrentUserHomePath() + "/Downloads/苹果自带macos mojove Mojave Night 莫哈韦沙漠晚上风景5K壁纸_彼岸图网.jpg", FileUtils.getCurrentUserHomePath() + "/Downloads/aaaa.jpeg", 500, 0.9, 99999999, 99999999); // 图片小于1000kb
    }
}
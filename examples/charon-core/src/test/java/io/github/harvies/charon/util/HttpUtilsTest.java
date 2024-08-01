package io.github.harvies.charon.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class HttpUtilsTest {

    @Test
    void getFileNameFromUrl() {
        String urlPath = "https://p3-sign.douyinpic.com/tos-cn-i-0813/4ff4928033d94f2586b793387145ffde~tplv-dy-lqenfix_nbhd:q86.jpeg?x-expires=1668430800&x-signature=iQZkZRHeUxEQJq3stWqvgOpb2lc%3D&from=3213915784&s=PackSourceEnum_DOUYIN_REFLOW&se=false&biz_tag=aweme_images&l=202210152104510102120700822D274EA8";
        String fileName = HttpUtils.getFileNameFromUrl(urlPath);
        System.out.println(fileName.substring(fileName.indexOf(".")));
    }
}
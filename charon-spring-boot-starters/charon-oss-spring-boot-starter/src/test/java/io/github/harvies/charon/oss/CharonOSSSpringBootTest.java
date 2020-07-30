package io.github.harvies.charon.oss;

import net.dongliu.requests.Requests;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import javax.annotation.Resource;

public class CharonOSSSpringBootTest extends BaseTest {

    @Resource
    private OSSService ossService;

    @Test
    public void test() {
        byte[] bytes = Requests.get("https://img95.699pic.com/photo/40100/6015.jpg_wh300.jpg")
                .send()
                .readToBytes();
        FileDTO fileDTO = ossService.upload(bytes, "测试图片.jpg");
        Assertions.assertNotNull(fileDTO);
    }
}

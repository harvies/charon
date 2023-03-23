//package io.github.harvies.charon.oss;
//
//import net.dongliu.requests.Requests;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//
//import jakarta.annotation.Resource;
//import java.io.IOException;
//
//public class CharonOSSSpringBootTest extends BaseTest {
//
//    private String fileUrl = "https://img95.699pic.com/photo/40100/6015.jpg_wh300.jpg";
//    @Resource
//    private OSSProvider ossProvider;
//
//    @Test
//    public void testUploadByByteArray() {
//        byte[] bytes = Requests.get(fileUrl)
//                .send()
//                .readToBytes();
//        FileDTO fileDTO = ossProvider.upload(bytes, "测试图片.jpg");
//        Assertions.assertNotNull(fileDTO);
//    }
//
//    @Test
//    public void testUploadByFileUrl() throws IOException {
//        FileDTO fileDTO = ossProvider.upload(fileUrl, "测试图片.jpg");
//        Assertions.assertNotNull(fileDTO);
//    }
//}

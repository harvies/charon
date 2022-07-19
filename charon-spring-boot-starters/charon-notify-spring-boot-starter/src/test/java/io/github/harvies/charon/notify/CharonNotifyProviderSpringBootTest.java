//package io.github.harvies.charon.notify;
//
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//
//import javax.annotation.Resource;
//
//public class CharonNotifyProviderSpringBootTest extends BaseTest {
//
//    @Resource
//    private NotifyProvider notifyProvider;
//
//    @Test
//    public void test() {
//        boolean send = notifyProvider.send("通知-测试标题", "通知-测试内容");
//        Assertions.assertTrue(send);
//    }
//
//    @Test
//    public void testMarkdown() {
//        boolean send = notifyProvider.send("通知-JetLinks 物联网基础平台 1.5 RELEASE 发布",
//                "### JetLinks 物联网基础平台 1.5 RELEASE 发布\n" +
//                        "\n" +
//                        "\t\tJetLinks 开源物联网平台 JetLinks 基于Java8,Spring Boot 2.x,WebFlux,Netty,Vert.x,Reactor等开发, 是一个开箱即用,可二次开发的企业级物联网基础平台。平台实现了物联网相关的众多基础功能, 能帮助你快速建立物联网相关业务系统。 核心特性 支持统一物模型管理,多种设备,多种厂家,统一管理。 统一设备连接管理,多协议...\n" +
//                        "\n" +
//                        "[查看原文](https://www.oschina.net/news/119055/jetlinks-1-5-release-released)");
//        Assertions.assertTrue(send);
//    }
//}

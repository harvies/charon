package io.github.harvies.charon.dubbo.spi;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.jupiter.api.Test;

/**
 * http://dubbo.apache.org/zh-cn/docs/source_code_guide/dubbo-spi.html
 */
public class DubboSPITest {

    @Test
    public void sayHello() {
        ExtensionLoader<Robot> extensionLoader =
                ExtensionLoader.getExtensionLoader(Robot.class);
        Robot optimusPrime = extensionLoader.getExtension("optimusPrime");
        optimusPrime.sayHello();
        Robot bumblebee = extensionLoader.getExtension("bumblebee");
        bumblebee.sayHello();
        System.out.println("sayHello end");

    }
}
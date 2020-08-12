package io.github.harvies.eris.spi;


import org.junit.jupiter.api.Test;

import java.util.ServiceLoader;

public class JavaSPITest {

    @Test
    public void sayHello() {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        System.out.println("Java SPI");
        serviceLoader.forEach(Robot::sayHello);
    }
}

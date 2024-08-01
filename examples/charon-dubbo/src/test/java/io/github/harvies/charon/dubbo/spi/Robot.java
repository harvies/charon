package io.github.harvies.charon.dubbo.spi;

import org.apache.dubbo.common.extension.SPI;

/**
 * @author harvies
 */
@SPI
public interface Robot {
    void sayHello();
}

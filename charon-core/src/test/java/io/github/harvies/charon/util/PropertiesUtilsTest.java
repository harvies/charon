package io.github.harvies.charon.util;

import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class PropertiesUtilsTest {

    @Test
    void getProperties() {
        Properties properties = PropertiesUtils.getDefaultProperties();
        assertNotNull(properties);
    }
}
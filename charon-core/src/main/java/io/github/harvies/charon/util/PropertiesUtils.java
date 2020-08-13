package io.github.harvies.charon.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class PropertiesUtils {

    public static Properties getDefaultProperties() {
        String path = io.github.harvies.charon.util.FileUtils.getCurrentUserHomePath() + "/.charon.properties";
        return getProperties(new File(path));
    }

    public static String getDefaultProperty(String key) {
        return getDefaultProperties().getProperty(key);
    }

    public static Properties getProperties(File file) {
        Properties properties = new Properties();
        String path = file.getAbsolutePath();
        try {
            properties.load(FileUtils.openInputStream(file));
        } catch (IOException e) {
            log.warn("file: {} not found!", path);
        }
        return properties;
    }
}

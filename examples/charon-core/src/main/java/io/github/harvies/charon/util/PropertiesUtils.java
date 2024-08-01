package io.github.harvies.charon.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class PropertiesUtils {

    private static final String CONFIG_PATH = "CHARON_CONFIG_PATH";

    public static String getDefaultPath() {
        String configPath = System.getenv(CONFIG_PATH);
        if (StringUtils.isNotBlank(configPath)) {
            return configPath;
        }
        String homePath = FileUtils.getCurrentUserHomePath() + "/.charon/charon.properties";
        if (FileUtils.getFile(homePath).exists()) {
            return homePath;
        }
        return "/opt/charon/charon.properties";
    }

    public static Properties getDefaultProperties() {
        String path = getDefaultPath();
        return getProperties(new File(path));
    }

    public static String getDefaultProperty(String key) {
        return getDefaultProperties().getProperty(key);
    }

    public static Properties getProperties(File file) {
        Properties properties = new Properties();
        String path = file.getAbsolutePath();
        try {
            try (FileInputStream fileInputStream = FileUtils.openInputStream(file)) {
                properties.load(fileInputStream);
            }
        } catch (IOException e) {
            log.warn("file: {} not found!", path);
        }
        return properties;
    }
}

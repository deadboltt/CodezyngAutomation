package com.codezyng.automation.config;

import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static Properties properties = new Properties();

    static {
        try {
            InputStream input =
                    ConfigReader.class
                            .getClassLoader()
                            .getResourceAsStream("config.properties");

            if (input == null) {
                throw new RuntimeException(
                        "❌ config.properties NOT FOUND in src/test/resources"
                );
            }

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException(
                    "❌ Failed to load config.properties",
                    e
            );
        }
    }

    private ConfigReader() {}

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException(
                    "❌ Property '" + key + "' not found in config.properties"
            );
        }
        return value;
    }
}

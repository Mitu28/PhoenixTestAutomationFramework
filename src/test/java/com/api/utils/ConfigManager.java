package com.api.utils;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private static final Properties prop = new Properties();
    private static final String path;


    private ConfigManager() {
    }

    static {
        // ✅ Read env properly
        String env = System.getProperty("env");

        // ✅ Default env if not passed
        if ( env == null || env.trim().isEmpty()) {
            env = "qa";
        }

        // ✅ Decide config file
        switch (env.toLowerCase()) {
            case "dev"-> path = "config/config.dev.properties";
            case "qa"-> path = "config/config.qa.properties";
            case "uat"-> path = "config/config.uat.properties";

            default-> throw new RuntimeException("Invalid env value: " + env);
        }

        // ✅ Load correct file
        InputStream input =
                Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream(path);

        if (input == null) {
            throw new RuntimeException("Cannot find config file at path: " + path);
        }

        try {
            prop.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file: " + path, e);
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }
}

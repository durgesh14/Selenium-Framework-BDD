package com.framework.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataReader {

    public static final String filePath = System.getProperty("user.dir") + "/config.properties";

    public static String getProperty(String key) throws IOException {
        Properties prop = new Properties();
        InputStream input = null;
        String configValue = null;

        try {
            input = new FileInputStream(filePath);
            prop.load(input);
            configValue = prop.getProperty(key);
        } finally {
            input.close();
        }
        return configValue;
    }

    public static String getUrlBasedOnTest(String testName) throws IOException {
        String key = testName + ".url";
        return getProperty(key);
    }


}

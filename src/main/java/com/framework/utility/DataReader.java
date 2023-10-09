package com.framework.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataReader {

    public static final String filePath = System.getProperty("user.dir") + "/config.properties";

    /**
     * Retrieves the value of the specified property key from a properties file.
     *
     * @param key The property key whose value is to be retrieved.
     * @return The value of the specified property key.
     * @throws IOException If an error occurs during file operations.
     */
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

    /**
     * Constructs a property key based on the specified test name,
     * and retrieves the value of the constructed key from a properties file.
     *
     * @param testName The name of the test.
     * @return The URL associated with the specified test name.
     * @throws IOException If an error occurs during file operations.
     */
    public static String getUrlBasedOnTest(String testName) throws IOException {
        String key = testName + ".url";
        return getProperty(key);
    }


}

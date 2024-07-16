package com.sparta.crh.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {

    private static final Properties properties = new Properties();

    static {
        try (InputStream inputStream = AppConfig.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new IOException("Unable to find config.properties");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getBaseUrl(){
        return properties.getProperty("Base_Url");
    }

    public static String getLoginUrl(){
        return properties.getProperty("Login_Url");
    }

    public static String getValidUsername(){
        return properties.getProperty("Username");
    }

    public static String getValidPassword(){
        return properties.getProperty("Password");
    }
}

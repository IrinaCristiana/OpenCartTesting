package com.endava.openCartTesting;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertyFile {

    protected Properties properties = new Properties();
    Thread currentThread = Thread.currentThread();
    ClassLoader contextClassLoader = currentThread.getContextClassLoader();
    InputStream inputStream = contextClassLoader.getResourceAsStream("config.properties");

    public ReadPropertyFile() throws IOException {

        if (inputStream != null) {
            properties.load(inputStream);
        }
    }
    public String getUrl(){
        return properties.getProperty("url");
    }

    public String getUser(){
        return properties.getProperty("user");
    }

    public String getPassword(){
        return properties.getProperty("password");
    }

}

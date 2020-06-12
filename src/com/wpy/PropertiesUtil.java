package com.wpy;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class PropertiesUtil {

    public static String getProperties(Properties properties, String key) throws UnsupportedEncodingException {
        return new String(properties.getProperty(key).getBytes("iso-8859-1"), "utf-8");
    }
}

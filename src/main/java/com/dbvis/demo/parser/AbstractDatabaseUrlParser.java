package com.dbvis.demo.parser;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDatabaseUrlParser implements DatabaseUrlParser {

    protected static Map<String, String> buildProperties(String propertiesStr) {
        Map<String, String> result = new HashMap<>();
        if (propertiesStr != null) {
            String[] props = propertiesStr.split("&");
            for (String prop : props) {
                String[] keyValue = prop.split("=", 2);
                if (keyValue.length == 2) {
                    result.put(keyValue[0], keyValue[1]);
                }
            }
        }
        return result;
    }

    protected abstract int getDefaultPort();

}

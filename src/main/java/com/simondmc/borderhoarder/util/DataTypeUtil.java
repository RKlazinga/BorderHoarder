package com.simondmc.borderhoarder.util;

import java.util.Map;
import java.util.Objects;

public class DataTypeUtil {
    // https://stackoverflow.com/a/2904266
    public static <T, String> T getKeyByCaseInsensitiveString(Map<T, String> map, String value) {
        for (Map.Entry<T, String> entry : map.entrySet()) {
            if (value.toString().equalsIgnoreCase((java.lang.String) entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}

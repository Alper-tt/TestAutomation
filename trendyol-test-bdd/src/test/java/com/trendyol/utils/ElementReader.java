package com.trendyol.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ElementReader {
    private static final Map<String, Locator> locatorMap = new HashMap<>();

    static {
        try (InputStream is = ElementReader.class.getClassLoader().getResourceAsStream("elements.json")) {
            if (is == null) throw new RuntimeException("elements.json not found!");
            JSONArray array = new JSONArray(new JSONTokener(is));
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                locatorMap.put(obj.getString("key"), new Locator(obj.getString("value"), obj.getString("type")));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load elements.json", e);
        }
    }

    public static Locator get(String key) {
        if (!locatorMap.containsKey(key)) throw new RuntimeException("Element not found: " + key);
        return locatorMap.get(key);
    }

    public static class Locator {
        public final String value;
        public final String type;

        public Locator(String value, String type) {
            this.value = value;
            this.type = type;
        }
    }
}

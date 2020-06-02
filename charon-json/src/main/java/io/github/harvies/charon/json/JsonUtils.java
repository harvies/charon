package io.github.harvies.charon.json;

import com.google.gson.Gson;

public class JsonUtils {
    private static final Gson gson = new Gson();

    public static String toJSONString(Object object) {
        return gson.toJson(object);
    }

    public static <T> T parseObject(String json, Class<T> objClass) {
        return gson.fromJson(json, objClass);
    }
}

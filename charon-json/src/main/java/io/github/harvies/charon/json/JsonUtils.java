package io.github.harvies.charon.json;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;

public class JsonUtils {
    private static final Gson gson;

    static {
        gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setDateFormat(DateFormat.LONG)
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .setVersion(1.0)
                .create();
    }

    public static String toJSONString(Object object) {
        return gson.toJson(object);
    }

    public static <T> T parseObject(String json, Class<T> objClass) {
        return gson.fromJson(json, objClass);
    }
}

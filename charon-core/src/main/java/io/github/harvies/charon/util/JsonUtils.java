package io.github.harvies.charon.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JsonUtils {

    public static String toJSONString(Object object) {
        return JSON.toJSONString(object);
    }

    public static <T> T parseObject(String json, Class<T> objClass) {
        return JSON.parseObject(json, objClass);
    }

    public static JSONObject parseObject(String json) {
        return JSONObject.parseObject(json);
    }
}

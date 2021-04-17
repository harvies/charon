package io.github.harvies.charon.util;

import io.github.harvies.charon.function.PropertyFunc;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

/**
 * https://blog.csdn.net/shu_xiaobai/article/details/99622908
 */
public class ReflectionFieldName {


    public static <T> String getFieldName(PropertyFunc<T, ?> func) {
        try {
            // 通过获取对象方法，判断是否存在该方法
            Method method = func.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            // 利用jdk的SerializedLambda 解析方法引用
            java.lang.invoke.SerializedLambda serializedLambda = (SerializedLambda) method.invoke(func);
            String getter = serializedLambda.getImplMethodName();
            return resolveFieldName(getter);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    private static String resolveFieldName(String getMethodName) {
        if (getMethodName.startsWith("get")) {
            getMethodName = getMethodName.substring(3);
        } else if (getMethodName.startsWith("is")) {
            getMethodName = getMethodName.substring(2);
        }
        // 小写第一个字母
        return firstToLowerCase(getMethodName);
    }

    private static String firstToLowerCase(String param) {
        if (StringUtils.isBlank(param)) {
            return "";
        }
        return param.substring(0, 1).toLowerCase() + param.substring(1);
    }
}

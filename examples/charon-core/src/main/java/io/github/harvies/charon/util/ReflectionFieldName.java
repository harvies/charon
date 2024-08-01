package io.github.harvies.charon.util;

import io.github.harvies.charon.function.PropertyFunc;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.Objects;

public class ReflectionFieldName {

    public static <T> String getFieldName(PropertyFunc<T, ?> lambda) {
        Objects.requireNonNull(lambda, "lambda expression cannot be null");
        SerializedLambda serializedLambda = getSerializedLambda(lambda);
        String methodName = serializedLambda.getImplMethodName();
        if (!methodName.startsWith("get") && !methodName.startsWith("is")) {
            throw new IllegalArgumentException("lambda expression must be a getter method reference");
        }
        return decapitalize(methodName.replaceFirst("^(get|is)", ""));
    }

    private static SerializedLambda getSerializedLambda(PropertyFunc<?, ?> lambda) {
        try {
            Method method = lambda.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(true);
            return (SerializedLambda) method.invoke(lambda);
        } catch (Exception e) {
            throw new RuntimeException("failed to get serialized lambda", e);
        }
    }

    private static String decapitalize(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }
        char[] chars = name.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return new String(chars);
    }
}
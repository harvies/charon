package io.github.harvies.eris.base.jdk.reflect;

import lombok.Data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectTest {
    public static void main(String[] args) throws IllegalAccessException {
        List<String> fieldList = new ArrayList<>();
        fieldList.add("a");
        fieldList.add("b");
        B b = new B();
        b.setA("aaa");
        b.setB("bbb");
        Field[] declaredFields = b.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (fieldList.contains(field.getName())) {
                System.err.println(field.get(b));
            }

        }
        Field[] declaredSuperFields = b.getClass().getSuperclass().getDeclaredFields();
        for (Field field : declaredSuperFields) {
            field.setAccessible(true);
            if (fieldList.contains(field.getName())) {
                System.err.println(field.get(b));
            }

        }
    }
}

@Data
class A {
    private String a;
}

@Data
class B extends A {
    private String b;
}

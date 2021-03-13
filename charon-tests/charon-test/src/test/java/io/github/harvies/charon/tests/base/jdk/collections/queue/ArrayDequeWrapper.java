package io.github.harvies.charon.tests.base.jdk.collections.queue;

import io.github.harvies.charon.util.JsonUtils;
import io.github.harvies.charon.util.reflect.FieldUtils;
import lombok.SneakyThrows;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayDequeWrapper<E> extends ArrayDeque<E> {
    public ArrayDequeWrapper() {
    }

    public ArrayDequeWrapper(int numElements) {
        super(numElements);
    }

    @Override
    public void addFirst(E e) {
        List<String> before = getPrint(e);
        super.addFirst(e);
        List<String> after = getPrint(e);
        System.out.println("before:" + before + "," + "after:" + after);
        System.out.println("-----------");
    }

    @Override
    public E pollFirst() {
        List<String> before = getPrint(null);
        E e = super.pollFirst();
        List<String> after = getPrint(e);
        System.out.println("before:" + before + "," + "after:" + after);
        System.out.println("-----------");
        return e;
    }

    @Override
    public E pollLast() {
        List<String> before = getPrint(null);
        E e = super.pollLast();
        List<String> after = getPrint(e);
        System.out.println("before:" + before + "," + "after:" + after);
        System.out.println("-----------");
        return e;
    }

    @Override
    public void addLast(E e) {
        List<String> before = getPrint(e);
        super.addLast(e);
        List<String> after = getPrint(e);
        System.out.println("before:" + before + "," + "after:" + after);
    }

    @SneakyThrows
    private List<String> getPrint(E e) {
        List<String> list = getFieldValueList(this, Arrays.asList("elements", "head", "tail"));
        if (e != null) {
            list.add("element:" + e);
        }
        return list;
    }

    private List<String> getFieldValueList(Object object, List<String> fieldNameList) throws IllegalAccessException {
        List<String> list = new ArrayList<>();
        for (String fieldName : fieldNameList) {
            String fieldValue = JsonUtils.toJSONString(FieldUtils.readField(object, fieldName, true));
            list.add(fieldName + ":" + fieldValue);
        }
        return list;
    }
}

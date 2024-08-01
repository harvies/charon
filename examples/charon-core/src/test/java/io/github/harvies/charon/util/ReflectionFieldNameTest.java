package io.github.harvies.charon.util;

import io.github.harvies.charon.function.PropertyFunc;
import lombok.Data;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ReflectionFieldNameTest {

    @Test
    void getFieldName() {
        assertThat(ReflectionFieldName.getFieldName((PropertyFunc<Person, String>) Person::getName), is("name"));
    }

    @Data
    public static class Person {
        private String name;
        private Long age;
    }
}

package io.github.harvies.charon.util;

import lombok.Data;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ReflectionFieldNameTest {

    @Test
    void getFieldName() {
        assertThat(ReflectionFieldName.getFieldName(Person::getName), is("name"));
    }

    @Data
    public static class Person {
        private String name;
        private Long age;

    }
}

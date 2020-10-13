package io.github.harvies.charon.tests.base.apache.commons.lang;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

public class ToStringTest {

    class A {
        private String a;
        private Integer b;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public Integer getB() {
            return b;
        }

        public void setB(Integer b) {
            this.b = b;
        }
    }

    @Test
    public void test() {
        A a = new A();
        a.setA("a");
        a.setB(1);
        System.err.println(a);
        System.err.println(ToStringBuilder.reflectionToString(a));
        System.err.println(ToStringBuilder.reflectionToString(a, ToStringStyle.JSON_STYLE));
        System.err.println(ToStringBuilder.reflectionToString(a, ToStringStyle.SIMPLE_STYLE));
        System.err.println(ToStringBuilder.reflectionToString(a, ToStringStyle.SHORT_PREFIX_STYLE));
        System.err.println(ToStringBuilder.reflectionToString(a, ToStringStyle.NO_CLASS_NAME_STYLE));
        System.err.println(ToStringBuilder.reflectionToString(a, ToStringStyle.NO_FIELD_NAMES_STYLE));
    }
}

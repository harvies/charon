package io.github.harvies.eris.base.jdk.clone;

import org.junit.Test;

public class CloneTest {

    @Test
    public void testNoClone() {
        Person p = new Person(1, "lisi");
        Person p1 = p;
        System.out.println(p);
        System.out.println(p1);
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        Person p = new Person(1, "lisi");
        Person p1 = (Person) p.clone();
        System.out.println(p);
        System.out.println(p1);
        boolean b = p.getName() == p1.getName();
        System.out.println("是否浅拷贝:" + b);
    }


}



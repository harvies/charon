package io.github.harvies.charon.tests.base.jdk.clone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
/**
 * 不实现Cloneable接口会报java.lang.CloneNotSupportedException
 */
public class Person implements Cloneable {
    private Integer id;
    private String name;

    /**
     * clone()为protected类型，不重写则无法访问
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person clone = (Person) super.clone();
        //不设置则为浅拷贝,name没有拷贝
        clone.setName(new String(name));

        return clone;
    }
}

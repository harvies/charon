package io.github.harvies.eris.base.datastructure.list;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * ArrayListClone测试(浅拷贝)
 *
 * @author harvies
 */
public class ArrayListCloneTest {
    public static void main(String[] args) {
        List<AAA> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            AAA aaa = new AAA();
            aaa.setAaa(String.valueOf(i));
            list.add(aaa);
        }
        AAA[] aaas1 = new AAA[7];
        aaas1[5]=new AAA();
        AAA[] aaas = list.toArray(aaas1);
        AAA aaa = aaas[0];
        aaa.setAaa("qqq");
        System.err.println(list.get(0));
    }

    @Data
    static class AAA {
        private String aaa;
    }
}

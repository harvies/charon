package io.github.harvies.eris.base.designpatterns.observer;

import java.util.ArrayList;
import java.util.List;

public class XiaoMei {
    List<Person> list = new ArrayList<Person>();

    public XiaoMei() {
    }

    public void addPerson(Person person) {
        list.add(person);
    }


    public void notifyPerson() {
        for (Person person : list) {
            person.getMessage("今天家里就我一个人，你们过来吧，谁先过来谁就能得到我!");
        }
    }
}

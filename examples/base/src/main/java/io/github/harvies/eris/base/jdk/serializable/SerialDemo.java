package io.github.harvies.eris.base.jdk.serializable;

import java.io.*;

public class SerialDemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //序列化
        FileOutputStream fos = new FileOutputStream("object.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        User user1 = new User("xuliugen", "123456", "male");
        oos.writeObject(user1);
        oos.flush();
        oos.close();
        //反序列化
        FileInputStream fis = new FileInputStream("object.out");
        ObjectInputStream ois = new ObjectInputStream(fis);
        User user2 = (User) ois.readObject();
        System.out.println(user2.getUserName() + " " +
                user2.getPassword() + " " + user2.getSex());
        //反序列化的输出结果为：xuliugen 123456 male
    }
}
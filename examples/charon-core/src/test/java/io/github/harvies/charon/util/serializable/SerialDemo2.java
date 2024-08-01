package io.github.harvies.charon.util.serializable;

import java.io.*;

public class SerialDemo2 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //序列化
        FileOutputStream fos = new FileOutputStream("object2.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        User2 user1 = new User2("xuliugen", "123456", "male");
        oos.writeObject(user1);
        oos.flush();
        oos.close();
        //反序列化
        FileInputStream fis = new FileInputStream("object2.out");
        ObjectInputStream ois = new ObjectInputStream(fis);
        User2 user2 = (User2) ois.readObject();
        System.out.println(user2.getUserName() + " " +
                user2.getPassword() + " " + user2.getSex());
        //反序列化的输出结果为：xuliugen 123456 male
    }
}
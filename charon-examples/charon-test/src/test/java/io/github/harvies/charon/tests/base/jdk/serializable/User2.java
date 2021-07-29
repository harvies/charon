package io.github.harvies.charon.tests.base.jdk.serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.*;

@Getter
@Setter
@AllArgsConstructor
//需要无参构造
@NoArgsConstructor
public class User2 implements Externalizable {
    private String userName;
    private String password;
    private String sex;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }
}
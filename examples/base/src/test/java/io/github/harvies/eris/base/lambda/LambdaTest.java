package io.github.harvies.eris.base.lambda;

import io.github.harvies.eris.base.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaTest {

    private static List<User> userList = new ArrayList<>();

    @Before
    public void before() {
        for (int i = 0; i < 5; i++) {
            userList.add(new User().setName("test").setId(Long.valueOf(i)).setAge(i).setEmail("mail" + i + "@mail.com"));
        }
    }

    @Test
    public void mapTest() {
        List<Long> idList = userList.stream().map(User::getId).collect(Collectors.toList());
        Assert.assertEquals(5, idList.size());
    }
}

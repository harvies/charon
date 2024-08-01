package io.github.harvies.charon.util.lambda;

import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaTest {

    private static final List<User> userList = new ArrayList<>();

    @Before
    public void before() {
        for (int i = 0; i < 5; i++) {
            userList.add(new User().setName("test").setId((long) i).setAge(i).setEmail("mail" + i + "@mail.com"));
        }
    }

    @Test
    public void mapTest() {
        List<Long> idList = userList.stream().map(User::getId).collect(Collectors.toList());
        Assert.assertEquals(5, idList.size());
    }

    /**
     * @author harvies
     */
    @Data
    @Accessors(chain = true)
    private static class User {
        private Long id;
        private String name;
        private Integer age;
        private String email;
    }

}

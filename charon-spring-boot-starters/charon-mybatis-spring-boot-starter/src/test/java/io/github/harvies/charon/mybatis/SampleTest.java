package io.github.harvies.charon.mybatis;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.github.harvies.charon.mybatis.entity.User;
import io.github.harvies.charon.mybatis.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        int count = 5;
        System.out.println(("----- selectAll method test ------"));
        for (int i = 0; i < count; i++) {
            userMapper.insert(new User().setName("test").setAge(i).setEmail("mail" + i + "@mail.com"));
        }
        List<User> selectList = userMapper.selectList(Wrappers.<User>lambdaQuery().eq(User::getName, "test"));
        Assert.assertEquals(count, selectList.size());
        List<Long> idList = selectList.stream().map(User::getId).collect(Collectors.toList());
        int deleteCount = userMapper.deleteBatchIds(idList);
        Assert.assertEquals(count, deleteCount);
    }

}

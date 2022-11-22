package io.github.harvies.charon.mybatis;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.github.harvies.charon.mybatis.po.UserPO;
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
            UserPO userPO = new UserPO();
            userPO .setName("test");
            userPO.setAge(i);
            userPO.setDeleted(0L);
            userPO.setEmail("mail" + i + "@mail.com");
            userPO.setBizId(1L);
            userMapper.insert(userPO);
        }
        List<UserPO> selectList = userMapper.selectList(Wrappers.<UserPO>lambdaQuery().eq(UserPO::getName, "test"));
        Assert.assertEquals(count, selectList.size());
        List<Long> idList = selectList.stream().map(UserPO::getId).collect(Collectors.toList());
        int deleteCount = userMapper.deleteBatchIds(idList);
        Assert.assertEquals(count, deleteCount);
    }

}

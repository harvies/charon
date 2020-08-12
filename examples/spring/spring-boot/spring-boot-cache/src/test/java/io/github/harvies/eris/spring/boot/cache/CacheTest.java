package io.github.harvies.eris.spring.boot.cache;

import com.google.common.collect.Lists;
import io.github.harvies.eris.spring.boot.cache.model.User;
import io.github.harvies.eris.spring.boot.cache.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 *
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheTest {
    @Autowired
    private UserService userService;

    @Test
    public void test() {
        for (int i = 0; i < 3; i++) {
            User user = userService.getById(1L);
            log.warn("user:{}", user);
        }
    }
    @Test
    public void testBatch() {
        for (int i = 0; i < 3; i++) {
            List<Long> userIdList= Lists.newArrayList();
            for (long j = 0; j <10 ; j++) {
                userIdList.add(j);
            }
            List<User> userList = userService.getByIdList(userIdList);
            log.warn("userList:{}", userList);
        }
        try {
            Thread.sleep(1100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

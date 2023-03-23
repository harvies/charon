package io.github.harvies.charon.shardingsphere.jdbc.controller;

import io.github.harvies.charon.shardingsphere.jdbc.entity.User;
import io.github.harvies.charon.shardingsphere.jdbc.mapper.UserMapper;
import io.github.harvies.charon.shardingsphere.jdbc.service.OtherService;
import io.github.harvies.charon.util.RandomValue;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private OtherService otherService;

    @Resource
    private UserMapper userMapper;

    @RequestMapping("/dropTable")
    public void dropTable() {
        userMapper.dropTable();
    }

    @RequestMapping("/createTableIfNotExists")
    public void createTableIfNotExists() {
        userMapper.createTableIfNotExists();
    }

    @RequestMapping("/truncateTable")
    public void truncateTable() {
        userMapper.truncateTable();
    }

    @SneakyThrows
    @RequestMapping("/insert")
    public void insert(int num) {
        for (int i = 0; i < num; i++) {
            User user = new User();
            user.setNickname(RandomValue.getChineseName());
            user.setMobile(RandomValue.getTel());
            user.setGmtCreate(new Date());
            user.setGmtModified(new Date());
            Thread.sleep(100);
            userMapper.insert(user);
        }
    }

    @RequestMapping("/migrate")
    public void migrate(Long from, Long to) {
        otherService.test(from, to);
    }

}

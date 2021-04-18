package io.github.harvies.charon.shardingsphere.jdbc.controller;

import io.github.harvies.charon.shardingsphere.jdbc.entity.User;
import io.github.harvies.charon.shardingsphere.jdbc.mapper.UserMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserMapper userMapper;

    @RequestMapping("/createTableIfNotExists")
    public void createTableIfNotExists() {
        userMapper.createTableIfNotExists();
    }

    @RequestMapping("/dropTable")
    public void dropTable() {
        userMapper.dropTable();
    }

    @RequestMapping("/insert")
    public void insert() {
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setNickname("nickname" + i);
            user.setMobile("mobile" + i);
            user.setGmtCreate(new Date());
            user.setGmtModified(new Date());
            userMapper.insert(user);
        }
    }
}

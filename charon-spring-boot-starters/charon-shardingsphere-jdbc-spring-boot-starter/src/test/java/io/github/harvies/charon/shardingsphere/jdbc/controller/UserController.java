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

    @RequestMapping("/insert")
    public void insert(int num) {
        for (int i = 0; i < num; i++) {
            User user = new User();
            user.setNickname("nickname" + i);
            user.setMobile("mobile" + i);
            user.setGmtCreate(new Date());
            user.setGmtModified(new Date());
            userMapper.insert(user);
        }
    }
}

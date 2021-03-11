package io.github.harvies.charon.redis.web;

import io.github.harvies.charon.redis.dto.UserDTO;
import io.github.harvies.charon.redis.service.UserCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserCacheService userCacheService;

    @RequestMapping(value = "/get/{id}")
    public UserDTO get(@PathVariable(value = "id") Long id) {
        UserDTO userDTO = userCacheService.get(id);
        log.info("get userDTO:[{}]", userDTO);
        return userDTO;
    }

    @RequestMapping(value = "/list")
    public List<UserDTO> list() {
        return userCacheService.list();
    }

    @RequestMapping(value = "/initDB")
    public void initDB() {
        userCacheService.initDB();
    }

}

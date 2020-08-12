package io.github.harvies.eris.spring.namespace.data.mybatis;

import io.github.harvies.eris.spring.namespace.data.mybatis.mapper.UserMapper;
import io.github.harvies.eris.spring.namespace.data.mybatis.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author harvies
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        applicationContext.setConfigLocation("classpath:applicationContext.xml");

        applicationContext.refresh();
        UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
        User user = userMapper.findById(1L);
        log.warn("user:{}", user);
    }
}

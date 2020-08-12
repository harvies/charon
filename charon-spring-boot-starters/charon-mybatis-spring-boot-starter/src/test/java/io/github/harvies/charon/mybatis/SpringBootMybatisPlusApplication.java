package io.github.harvies.charon.mybatis;

import io.github.harvies.charon.mybatis.entity.User;
import io.github.harvies.charon.mybatis.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * @author harvies
 */
@SpringBootApplication
@MapperScan("io.github.harvies.charon.mybatis.mapper")
public class SpringBootMybatisPlusApplication implements CommandLineRunner {

    /**
     * \@Autowired 按类型注入 @Resource按名称注入
     */
    @Autowired
    private UserMapper userMapper;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisPlusApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }
}

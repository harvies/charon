package io.github.harvies.charon.shardingsphere.jdbc.service;

import io.github.harvies.charon.shardingsphere.jdbc.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Slf4j
public class OtherServiceImpl implements OtherService {
    @Resource
    private UserService userService;

    @Override
    @Transactional
    public void test(Long from, Long to) {
        log.info("begin test: from:[{}] to:[{}]", from, to);
        User toUser = userService.selectById(to);
        toUser.setGmtModified(new Date());
        userService.updateById(toUser);
        userService.migrate(from, to);
        log.info("end test: from:[{}]", from);
    }
}

package io.github.harvies.charon.spring.boot.test.web.inject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public abstract class BaseService {
    public void hello() {
        log.info("hello");
    }
}

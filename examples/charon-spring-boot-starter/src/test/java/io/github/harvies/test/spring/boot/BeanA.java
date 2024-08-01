package io.github.harvies.test.spring.boot;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class BeanA {
    private String name;

    public boolean methodA() {
        log.info("methodA executed");
        return true;
    }
}

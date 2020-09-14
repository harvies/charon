package io.github.harvies.charon.spring.boot.test.web.inject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Service
@Slf4j
public class CService extends BaseService {

    @PostConstruct
    public void init() {
        log.info("init");

        aService.hello();
        bService.hello();
    }

    @Resource
    private BService bService;
    @Resource
    private AService aService;
}

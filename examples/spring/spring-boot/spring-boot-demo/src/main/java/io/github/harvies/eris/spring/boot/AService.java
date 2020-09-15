package io.github.harvies.eris.spring.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Service
@Slf4j
public class AService extends BaseService {

    @PostConstruct
    public void init() {
        log.info("init");

        bService.hello();
        cService.hello();
    }

    @Resource
    private BService bService;
    @Resource
    private CService cService;
}

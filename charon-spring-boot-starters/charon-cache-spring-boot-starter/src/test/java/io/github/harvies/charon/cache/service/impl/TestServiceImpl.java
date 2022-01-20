package io.github.harvies.charon.cache.service.impl;

import io.github.harvies.charon.cache.request.TestRequest;
import io.github.harvies.charon.cache.service.TestService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public TestRequest echo(@RequestBody TestRequest testRequest) {
        System.out.println("echo-echo-echo");
        testRequest.setA(1);
        return testRequest;
    }
}

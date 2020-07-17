package io.github.harvies.charon.apollo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Value("${value}")
    private String test1;

    @RequestMapping("/get")
    public String get() {
        return test1;
    }
}

package io.github.harvies.test.spring.boot.webflux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/json")
public class TestJsonController {

    @GetMapping("/get")
    public Object get() {
        Map<String, Object> map = new HashMap<>();
        map.put("aa", "aaaa");
        map.put("bbb", new Date());
        map.put("cc", "张三");
        return map;
    }
}

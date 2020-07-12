package io.github.harvies.charon.spring.boot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/json")
public class TestJsonController {

    @GetMapping("/get")
    public Object get() {
        Map<String, String> map = new HashMap<>();
        map.put("aa", "aaaa");
        return map;
    }
}

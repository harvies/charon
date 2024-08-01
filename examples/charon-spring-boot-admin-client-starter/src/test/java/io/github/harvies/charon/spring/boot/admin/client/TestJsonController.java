package io.github.harvies.charon.spring.boot.admin.client;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//RestController 只表示返回文本,produces知道返回json
@RestController
@RequestMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestJsonController {

    @GetMapping(value = "/get")
    public Object get() {
        Map<String, Object> map = new HashMap<>();
        map.put("aa", "aaaa");
        map.put("bbb", new Date());
        return map;
    }
}

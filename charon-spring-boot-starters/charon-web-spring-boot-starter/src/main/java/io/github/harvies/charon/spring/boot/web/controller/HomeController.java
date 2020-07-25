package io.github.harvies.charon.spring.boot.web.controller;

import io.github.harvies.charon.spring.boot.web.Constants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return Constants.SUCCESS;
    }
}

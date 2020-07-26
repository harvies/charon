package io.github.harvies.charon.spring.boot.web.controller;

import io.github.harvies.charon.spring.boot.web.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/health")
public class HealthController {

    @RequestMapping("/status")
    public String status() {
        return Constants.SUCCESS;
    }
}

package io.github.harvies.charon.spring.boot.web.controller.system;

import io.github.harvies.charon.spring.boot.web.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/health")
public class HealthController {

    @RequestMapping(value = "/status")
    public String status() {
        return Constants.SUCCESS;
    }
}

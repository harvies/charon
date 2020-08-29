package io.github.harvies.charon.spring.boot.web.controller.monitor;

import io.github.harvies.charon.spring.boot.web.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/health")
public class HealthController {

    public String status = Constants.SUCCESS;

    @RequestMapping(value = "/status", produces = MediaType.TEXT_PLAIN_VALUE)
    public String status() {
        return status;
    }
}

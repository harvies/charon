package io.github.harvies.charon.spring.boot.web.controller.monitor;

import io.github.harvies.charon.spring.boot.web.result.ResultDTO;
import io.github.harvies.charon.spring.boot.web.result.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
public class HealthController {

    @RequestMapping("/status")
    public ResultDTO<String> status() {
        return Results.success("success");
    }
}

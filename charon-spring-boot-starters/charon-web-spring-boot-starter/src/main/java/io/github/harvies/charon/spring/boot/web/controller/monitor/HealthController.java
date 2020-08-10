package io.github.harvies.charon.spring.boot.web.controller.monitor;

import io.github.harvies.charon.spring.boot.web.result.ResultDTO;
import io.github.harvies.charon.spring.boot.web.result.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/health")
public class HealthController {

    @RequestMapping("/status")
    public ResultDTO<String> status() {
        return Results.success("success");
    }
}

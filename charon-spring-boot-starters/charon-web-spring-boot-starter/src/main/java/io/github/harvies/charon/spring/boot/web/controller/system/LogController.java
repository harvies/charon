package io.github.harvies.charon.spring.boot.web.controller.system;

import io.github.harvies.charon.spring.boot.web.Constants;
import io.github.harvies.charon.util.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/log")
public class LogController {

    @RequestMapping(value = "/list")
    public List<? extends Logger> list() {
        return LogUtils.list();
    }

    @RequestMapping(value = "/set")
    public String set(String name, String level) {
        LogUtils.set(name, level);
        return Constants.SUCCESS;
    }
}

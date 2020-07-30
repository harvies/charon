package io.github.harvies.charon.spring.boot.web.controller.monitor;

import lombok.SneakyThrows;
import net.bull.javamelody.internal.model.HeapHistogram;
import net.bull.javamelody.internal.model.VirtualMachine;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/heap", produces = MediaType.APPLICATION_JSON_VALUE)
public class HeapController {
    @SneakyThrows
    @RequestMapping(value = "/histogram")
    public HeapHistogram histogram() {
        return VirtualMachine.createHeapHistogram();

    }
}

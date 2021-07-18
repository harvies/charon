package io.github.harvies.charon.test.ribbon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class CharonRibbonApplication {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(CharonRibbonApplication.class, args);
    }

    @RestController
    static class TestController {

        private final RestTemplate restTemplate;

        @Autowired
        public TestController(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
        }

        @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
        public String echo(@PathVariable(value = "str") String str) {
            return restTemplate.getForObject("http://charon-ribbon/echo1/" + str, String.class);
        }
    }

    @RestController
    static class EchoController {
        @RequestMapping(value = "/echo1/{str}", method = RequestMethod.GET)
        public String echo(@PathVariable(value = "str") String string) {
            log.info("echo str:[{}]", string);
            return "Hello Nacos Discovery " + string;
        }
    }
}
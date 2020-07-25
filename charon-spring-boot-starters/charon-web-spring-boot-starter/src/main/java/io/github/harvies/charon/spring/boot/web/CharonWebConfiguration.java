package io.github.harvies.charon.spring.boot.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"io.github.harvies.charon.spring.boot.web"})
public class CharonWebConfiguration {

}

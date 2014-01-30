package io.spring.web.mvc.rest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "io.spring.web.mvc.rest.controller",
		"io.spring.web.mvc.rest.service" })
public class RESTConfig {

}

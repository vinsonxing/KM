package com.study.spring.mvc.ModelAttribute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MAApplication {
    static Logger log = LoggerFactory.getLogger(MAApplication.class);
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MAApplication.class, args);
    }
}

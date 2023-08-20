package com.study.spring.bean.LifeCycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
@SpringBootApplication
public class TestLifeCycle {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TestLifeCycle.class, args);
        context.close();
    }
}

package com.study.spring.bean.InitDestroy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        context.close();
    }
    @Bean(initMethod="init3")
    public Bean1 bean1(){
        return new Bean1();
    }

    @Bean(destroyMethod = "destroy3")
    public Bean2 bean2(){
        return new Bean2();
    }
}

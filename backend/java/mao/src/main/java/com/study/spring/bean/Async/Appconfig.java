package com.study.spring.bean.Async;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@ComponentScan
@EnableAsync
public class Appconfig {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Appconfig.class);
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
//        HelloService service = (HelloService) context.getBean("helloServiceImpl");
//        System.out.println("type is " + service.getClass());
        HelloService1 service = (HelloService1) context.getBean("helloServiceImpl1");
        System.out.println("type is " + service.getClass());

        service.hello2();
    }

}

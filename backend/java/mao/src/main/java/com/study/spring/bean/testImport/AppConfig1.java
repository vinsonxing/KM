package com.study.spring.bean.testImport;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(AppConfig.class)
@Configuration
@ComponentScan
public class AppConfig1 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig1.class);
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>");
        BaseConfig baseconfig = context.getBean(BaseConfig.class);
        AppConfig config = context.getBean(AppConfig.class);
        System.out.println(baseconfig.getClass());
        System.out.println(config.getClass());
        System.out.println(config.getCat().getClass());

        System.out.println(config.getCat());
        System.out.println(config.getCat());
        System.out.println(config.getCat());

        System.out.println(config.getMouse());
        System.out.println(config.getMouse());
        System.out.println(config.getMouse());
    }
}

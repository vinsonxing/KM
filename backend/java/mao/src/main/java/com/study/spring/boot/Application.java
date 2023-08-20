package com.study.spring.boot;

import org.springframework.boot.autoconfigure.AutoConfigurationImportSelector;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.*;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;

import java.util.List;

public class Application {
    /*public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.getDefaultListableBeanFactory().setAllowBeanDefinitionOverriding(false);
        context.registerBean("config", Config.class);
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.refresh();
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>");
        System.out.println(context.getBean(Bean1.class));
    }

    @Configuration
    @Import(MyImportSelector.class)
//    @Import(AutoConfigurationImportSelector.class)
//    @EnableAutoConfiguration
    static class Config { //本项目配置类
//        @Bean
//        public Bean1 bean1() {
//            return new Bean1("本项目");
//        }
    }

    static class MyImportSelector implements DeferredImportSelector {
        @Override
        public String[] selectImports(AnnotationMetadata importingClassMetadata) {
            List<String> myImportSelectors = SpringFactoriesLoader.loadFactoryNames(MyImportSelector.class, null);
            return myImportSelectors.toArray(new String[0]);
        }
    }

    @Configuration //第三方配置类
    static class AutoConfiguration1 {
        @Bean
        @ConditionalOnMissingBean
        public Bean1 bean1() {
            return new Bean1("第三方");
        }
    }

    static class Bean1 {
        private String name;

        public Bean1() {
            System.out.println("构造Bean1");
        }

        public Bean1(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Bean1{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    @Configuration //第三方配置类
    static class AutoConfiguration2 {
        @Bean
        public Bean2 bean2() {
            return new Bean2();
        }
    }

    static class Bean2 {
        private String name;

        public Bean2() {
            System.out.println("构造Bean2");
        }

        public Bean2(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Bean2{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }*/
}

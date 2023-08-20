package com.study.spring.bean.Aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

public class MyConfig {
    @Autowired
    public void  setApplicationContext(ApplicationContext applicationContext) {
        System.out.println("当前bean " + this + " @Autowired容器是 " + applicationContext);
    }
    @PostConstruct
    public void init(){
        System.out.println("当前bean " + this + " @PostConstruct初始化");
    }
    @Bean
    public BeanFactoryPostProcessor processor(){
        return beanFactory -> {
            System.out.println("执行 processor");
        };
    }
    //下面的方法不会导致解析autowired的和解析post construct的注解不能工作，因为涉及到post processor 的顺序
    /*@Bean
    public InstantiationAwareBeanPostProcessor processor(){
        return new InstantiationAwareBeanPostProcessor() {
            @Override
            public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
                return null;
            }

            @Override
            public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
                return false;
            }
        };
    }*/

}

package com.study.spring.bean.Aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;

public class MyNewConfig implements ApplicationContextAware, InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("当前bean " + this + " InitializingBean 初始化");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("当前bean " + this + " Aware 容器是 " + applicationContext);
    }

    @Bean
    public BeanFactoryPostProcessor newprocessor(){
        return beanFactory -> {
            System.out.println("执行 newprocessor");
        };
    }
}

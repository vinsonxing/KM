package com.study.spring.bean.Aware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

public class Application {

    static Logger logger = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        //context.registerBean("myBean", MyBean.class);
        context.registerBean("myConfig", MyConfig.class);
        context.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        context.registerBean(CommonAnnotationBeanPostProcessor.class);
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.refresh(); //1. beanFactory 后处理器 2. 添加bean处理器 3. 初始化单例
        context.close();
    }
}

package com.study.spring.bean.Aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;

public class MyBean implements BeanNameAware, ApplicationContextAware, InitializingBean {
    @Override
    public void setBeanName(String s) {
        System.out.println("当前bean " + this + "名字叫 " + s);
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("当前bean " + this + "容器是 " + applicationContext);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("当前bean " + this + "初始化");
    }


}

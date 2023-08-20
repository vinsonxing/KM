package com.study.spring.bean.InitDestroy;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

public class Bean1 implements InitializingBean {
    public Bean1() {
    }

    @PostConstruct
    public void init(){
        System.out.println("PostConstruct 被调用");
    }
    // Aware 接口在这里执行
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet 被调用");
    }

    public void init3(){
        System.out.println("initMethod 被调用");
    }
}

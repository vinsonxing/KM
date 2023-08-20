package com.study.spring.bean.InitDestroy;

import org.springframework.beans.factory.DisposableBean;

import javax.annotation.PreDestroy;

public class Bean2 implements DisposableBean {
    public Bean2() {
    }

    public void destroy3(){
        System.out.println("destroyMethod 被调用");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean destroy方法被调用");
    }

    @PreDestroy
    public void destroy1(){
        System.out.println("PreDestroy 被调用");
    }
}

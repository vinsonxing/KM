package com.study.spring.bean.BeanFactoryPostProcessor.component;

public class Bean1 {
    public Bean1() {
        System.out.println("Bean1 被 spring 管理了！" );
    }

    public void init(){
        System.out.println("Bean1 init 被调用");
    }
}

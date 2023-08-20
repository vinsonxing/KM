package com.study.spring.bean.BeanFactoryPostProcessor.component;

import org.springframework.stereotype.Component;

@Component
public class Bean3 {
    public Bean3() {
        System.out.println("Bean3 被 spring 管理了！");
    }
}

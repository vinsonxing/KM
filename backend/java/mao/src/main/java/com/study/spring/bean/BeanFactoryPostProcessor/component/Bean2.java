package com.study.spring.bean.BeanFactoryPostProcessor.component;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class Bean2 {
    public Bean2() {
        System.out.println("Bean2 被 spring 管理了！");
    }
}

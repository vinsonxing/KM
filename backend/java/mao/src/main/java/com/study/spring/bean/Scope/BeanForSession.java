package com.study.spring.bean.Scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Scope("session")
@Component
public class BeanForSession {
    @PreDestroy
    public void destroy(){
        System.out.println("session bean 销毁");
    }
}

package com.study.spring.bean.Scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Scope("request")
@Component
public class BeanForRequest {
    @PreDestroy
    public void destroy(){
        System.out.println("request bean 销毁");
    }
}

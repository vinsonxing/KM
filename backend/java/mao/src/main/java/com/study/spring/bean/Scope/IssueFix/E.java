package com.study.spring.bean.Scope.IssueFix;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class E {
    @Lazy
    @Autowired
    F1 f1;

    public F1 getF1() {
        return f1;
    }

    @Autowired
    F2 f2;

    public void setF1(F1 f1) {
        this.f1 = f1;
    }

    public F2 getF2() {
        return f2;
    }

    @Autowired
    private ObjectFactory<F3> f3;

    public F3 getF3() {
        return f3.getObject();
    }

    @Autowired
    private ApplicationContext context;

    public F4 getF4(){
        return context.getBean(F4.class);
    }
}

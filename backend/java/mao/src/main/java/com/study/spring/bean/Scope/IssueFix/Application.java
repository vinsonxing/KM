package com.study.spring.bean.Scope.IssueFix;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        E e = context.getBean(E.class);
        System.out.println(e.getF1().getClass());
        System.out.println(e.getF1());
        System.out.println(e.getF1());
        System.out.println(e.getF1());

        System.out.println(e.getF2().getClass());
        System.out.println(e.getF2());
        System.out.println(e.getF2());
        System.out.println(e.getF2());

        System.out.println(e.getF3().getClass());
        System.out.println(e.getF3());
        System.out.println(e.getF3());

        System.out.println(e.getF4().getClass());
        System.out.println(e.getF4());
        System.out.println(e.getF4());
        context.close();
    }
}

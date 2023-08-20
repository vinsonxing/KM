package com.study.spring.bean.testBeanConflict;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class,Application1.class);
        //context.getBean("pmAreasTService05");
        for (String name : context.getBeanDefinitionNames()) {
            //System.out.println(name);
        }
        //ConflictBeans conflictBean = context.getBean(ConflictBeans.class);
        System.out.println(context.getBean("pmAreasTService05").getClass());
        System.out.println(">>>>>>>>>>>>>");
//        System.out.println(conflictBean.pmAreasTService01);
//        System.out.println(conflictBean.pmAreasTService02);
        context.close();
    }

    @Bean(name = "pmAreasTService05")
    public PmAreasTService01 pmAreasTService01() {
        System.out.println("构造PmAreasTService01");
        return new PmAreasTService01();
    }

    @Bean(name = "pmAreasTService05")
    public PmAreasTService02 pmAreasTService02() {
        System.out.println("构造PmAreasTService02");
        return new PmAreasTService02();
    }


}




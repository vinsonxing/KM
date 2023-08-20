package com.study.spring.bean.PostProcessor;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.support.GenericApplicationContext;

public class TestBeanPostProcessor {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("bean1", Bean1.class);
        context.registerBean("bean2", Bean2.class);
        context.registerBean("bean3", Bean3.class);
        context.registerBean("bean4", Bean4.class);

        context.getDefaultListableBeanFactory().setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        context.registerBean(AutowiredAnnotationBeanPostProcessor.class); //依赖注入阶段@Autowired @Value
        context.registerBean(CommonAnnotationBeanPostProcessor.class); //依赖注入阶段@Resource 初始化前@PostConstruct 销毁前@PreDestroy
        ConfigurationPropertiesBindingPostProcessor.register(context.getDefaultListableBeanFactory()); //@ConfigurationProperties

        context.refresh();
        System.out.println(context.getBean(Bean4.class));
        context.close();
    }
}

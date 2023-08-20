package com.study.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

import javax.annotation.PostConstruct;

public class ProxyCreationTiming {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.registerBean(Config.class);
        context.refresh();
        Bean1 bean1 = context.getBean(Bean1.class);
        bean1.foo();
        context.close();

        // 创建 -》 （*）依赖注入 -》初始化 （*）
        // 代理创建时机
        //     1.初始化之后（无循环依赖时）
        //     2.实例创建后，依赖注入前（有循环依赖时），并暂存于二级缓存
        // 依赖注入与初始化不应被增强，仍应被施加于原始对象
    }
    @Configuration
    static class Config{
        @Bean
        public AnnotationAwareAspectJAutoProxyCreator annotationAwareAspectJAutoProxyCreator(){
            return new AnnotationAwareAspectJAutoProxyCreator();
        }
        @Bean
        public AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor(){
            return new AutowiredAnnotationBeanPostProcessor();
        }
        @Bean
        public CommonAnnotationBeanPostProcessor commonAnnotationBeanPostProcessor(){
            return new CommonAnnotationBeanPostProcessor();
        }
        @Bean
        public Advisor advisor(MethodInterceptor advice){
            AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
            pointcut.setExpression("execution(* foo())");
            return new DefaultPointcutAdvisor(pointcut, advice);
        }
        @Bean
        public MethodInterceptor advice(){
            return invocation -> {
                System.out.println("before...");
                Object result = invocation.proceed();
                System.out.println("after...");
                return result;
            };
        }
        @Bean
        public Bean1 bean1(){
            return new Bean1();
        }
        @Bean
        public Bean2 bean2(){
            return new Bean2();
        }

    }
    static class Bean1{
        public void foo(){
        }
        public Bean1(){
            System.out.println("Bean1()");
        }
        @PostConstruct
        public void init(){
            System.out.println("Bean1 init()");
        }
    }
    static class Bean2{
        public Bean2(){
            System.out.println("Bean2()");
        }
        @Autowired
        public void setBean1(Bean1 bean1){
            System.out.println("Bean2 setBean1(bean1) class is:" + bean1.getClass());
        }
        @PostConstruct
        public void init(){
            System.out.println("Bean2 init()");
        }
    }
}

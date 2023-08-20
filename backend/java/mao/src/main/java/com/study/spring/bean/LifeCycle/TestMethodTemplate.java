package com.study.spring.bean.LifeCycle;

import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

public class TestMethodTemplate {
    public static void main(String[] args) {
        MyBeanFactory beanFactory = new MyBeanFactory();
        beanFactory.addBeanPostProcessor(bean -> System.out.println("解析@Autowired"));
        beanFactory.addBeanPostProcessor(bean -> System.out.println("解析@Resource"));
        beanFactory.getBean();
    }
    static class MyBeanFactory{
        public Object getBean(){
            Object bean = new Object();
            System.out.println("构造 " + bean);
            System.out.println("依赖注入 " + bean);
            for (BeanPostProcessor processor : processorList) {
                processor.inject(bean);
            }
            System.out.println("初始化 " + bean);
            return bean;
        }
        List<BeanPostProcessor> processorList = new ArrayList<>();
        public void addBeanPostProcessor(BeanPostProcessor processor){
            processorList.add(processor);
        }
    }
    static interface BeanPostProcessor {
        public void inject(Object bean); //依赖注入阶段扩展
    }
}

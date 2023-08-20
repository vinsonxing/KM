package com.study.spring.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Configuration
public class Application2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application2.class);
        context.getBean(MyService.class).doBusiness();
        context.close();
    }

    @Component
    static class MyService {
        private static final Logger log = LoggerFactory.getLogger(MyService.class);
        @Autowired
        private ApplicationEventPublisher publisher;

        public void doBusiness() {
            log.info("主线业务");
            publisher.publishEvent(new MyEvent("MyService.doBusiness"));
        }
    }
//    @Component
//    static class SmsApplicationListener implements ApplicationListener<MyEvent> {
//        private static final Logger log = LoggerFactory.getLogger(SmsApplicationListener.class);
//        @Override
//        public void onApplicationEvent(MyEvent event) {
//            log.info("发送短信");
//        }
//    }
//    @Component
//    static class EmailApplicationListener implements ApplicationListener<MyEvent>{
//        private static final Logger log = LoggerFactory.getLogger(EmailApplicationListener.class);
//        @Override
//        public void onApplicationEvent(MyEvent event) {
//            log.info("发送邮件");
//        }
//    }

    @Component
    static class SmsService {
        private static final Logger log = LoggerFactory.getLogger(SmsService.class);

        @MyListener
        public void listener(MyEvent myEvent) {
            log.info("发送短信");
        }
    }

    @Component
    static class EmailService {
        private static final Logger log = LoggerFactory.getLogger(EmailService.class);

        @MyListener
        public void listener(MyEvent myEvent) {
            log.info("发送邮件");
        }
    }

    @Bean
    public SmartInitializingSingleton smartInitializingSingleton(ConfigurableApplicationContext context){
        SmartInitializingSingleton singleton = new SmartInitializingSingleton() {
            @Override
            public void afterSingletonsInstantiated() {
                for (String name : context.getBeanDefinitionNames()) {
                    Object bean = context.getBean(name);
                    for (Method method : bean.getClass().getMethods()) {
                        if (method.isAnnotationPresent(MyListener.class)) {
                            ApplicationListener listener = new ApplicationListener() {
                                @Override
                                public void onApplicationEvent(ApplicationEvent event) {
                                    System.out.println(event);
                                    Class<?> eventType = method.getParameterTypes()[0];
                                    if (eventType.isAssignableFrom(event.getClass())) {
                                        try {
                                            method.invoke(bean, event);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            };
                            context.addApplicationListener(listener);
                        }
                    }
                }
            }
        };
        return singleton;
    }

    static class MyEvent extends ApplicationEvent {

        public MyEvent(Object source) {
            super(source);
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface MyListener {
    }
}

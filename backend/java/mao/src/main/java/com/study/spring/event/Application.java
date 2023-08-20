package com.study.spring.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
@Configuration
public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        context.getBean(MyService.class).doBusiness();
        context.close();
    }

    @Component
    static class MyService{
        private static final Logger log = LoggerFactory.getLogger(MyService.class);
        @Autowired
        private ApplicationEventPublisher publisher;
        public void doBusiness(){
            log.info("主线业务");
            publisher.publishEvent(new MyEvent("MyService.doBusiness"));
        }
    }
    @Component
    static class SmsApplicationListener implements ApplicationListener<MyEvent>{
        private static final Logger log = LoggerFactory.getLogger(SmsApplicationListener.class);
        @Override
        public void onApplicationEvent(MyEvent event) {
            log.info("发送短信");
        }
    }
    @Component
    static class EmailApplicationListener implements ApplicationListener<MyEvent>{
        private static final Logger log = LoggerFactory.getLogger(EmailApplicationListener.class);
        @Override
        public void onApplicationEvent(MyEvent event) {
            log.info("发送邮件");
        }
    }


    static class MyEvent extends ApplicationEvent{

        public MyEvent(Object source) {
            super(source);
        }
    }
}

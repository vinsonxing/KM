package com.study.spring.bean.Async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;

//@Service
public class HelloServiceImpl implements HelloService{
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Object hello() {
        System.out.println("hello 当前线程：" + Thread.currentThread().getName());
        // 从容器里拿到该类型的实例~~~~（注意：若是JDK代理，这里的类型只能传接口，而不能是实现类，否则NoSuchBean...）
        HelloService helloService = applicationContext.getBean(HelloService.class);
        System.out.println(helloService.getClass());
        // 然后用本实例去调用  而不是用默认的this去调用
//        helloService.hello2();
        this.hello2();
        return "service hello";
    }

    @Async
    @Override
    public Object hello2() {
        System.out.println("hello2 当前线程：" + Thread.currentThread().getName());
        return null;
    }

}

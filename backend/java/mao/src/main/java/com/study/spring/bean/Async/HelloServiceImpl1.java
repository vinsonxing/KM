package com.study.spring.bean.Async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl1 extends HelloService1{
    @Autowired
    private ApplicationContext applicationContext;

    public Object hello() {
        System.out.println("hello 当前线程：" + Thread.currentThread().getName());
        // 从容器里拿到该类型的实例~~~~（注意：若是JDK代理，这里的类型只能传接口，而不能是实现类，兄弟不能覆盖，否则NoSuchBean...）
        HelloService1 helloService1 = applicationContext.getBean(HelloService1.class);
        System.out.println(helloService1.getClass());
        // 然后用本实例去调用  而不是用默认的this去调用
        helloService1.hello2();
//        this.hello2();
        return "service hello";
    }

    @Async
    public Object hello2() {
        System.out.println("hello2 当前线程：" + Thread.currentThread().getName());
        return null;
    }

}

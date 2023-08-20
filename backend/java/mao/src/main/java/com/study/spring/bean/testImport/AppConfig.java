package com.study.spring.bean.testImport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
/*
@Configuration加上是full模式，会为其生成代理，调用getCat会返回唯一单例
如果去掉是lite模式，相当于普通方法调用

在有Configuration的情况下如果想达到多例效果，需要把cat也纳入容器管理，并且通过proxyMode=target class,可以为其生成代理，所以要加ComponentScan
补充bean定义是通过bean工厂后处理器，是在初始化之后，所以mouse作为属性要实现多例注入则应该首先纳入容器管理，可以在属性上加@Lazy或者是目标上加proxyMode
 */
@Configuration
//@ComponentScan
public class AppConfig extends BaseConfig{
//    @Autowired
//    Mouse mouse;

    @Bean("cat")
    public Cat getCat() {
        return new Cat();
    }

//    public Mouse getMouse() {
//        return mouse;
//    }
}

//@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
//@Component
class Cat {

}

//@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
//@Component
//class Mouse {
//
//}

package com.study.spring.bean.BeanFactoryPostProcessor;

import com.study.spring.bean.BeanFactoryPostProcessor.mapper.Mapper1;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config", Config.class);
//        context.registerBean(ConfigurationClassPostProcessor.class); //ComponentScan @Bean @Import @ImportResource
//        context.registerBean(MapperScannerConfigurer.class, bd -> {
//            bd.getPropertyValues().add("basePackage", "com.spring.study.bean.BeanFactoryPostProcessor.mapper");
//        });

//        context.registerBean(ComponentScanPostProcessor.class);
        context.registerBean(AtBeanPostProcessor.class);
        context.registerBean(MapperPostProcessor.class);

        context.refresh();

        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        System.out.println(context.getBean("&sqlSessionFactoryBean1").getClass());
        //Mapper1 mapper1 = context.getBean(Mapper1.class);
        //System.out.println(mapper1);
        context.close();
    }


}

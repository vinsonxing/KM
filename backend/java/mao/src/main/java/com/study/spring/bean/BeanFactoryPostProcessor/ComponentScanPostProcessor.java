package com.study.spring.bean.BeanFactoryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.io.IOException;

public class ComponentScanPostProcessor implements BeanFactoryPostProcessor {
    @Override // context.refresh()时调用
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        try {
            ComponentScan componentScan = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);
            if (componentScan != null) {
                for (String s : componentScan.basePackages()) {
                    System.out.println(">>>>>>" + s);
                    CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();

                    String path = "classpath*:" + s.replace(".", "/") + "/**/*.class";
                    Resource[] resources = new PathMatchingResourcePatternResolver().getResources(path);
                    AnnotationBeanNameGenerator generator = new AnnotationBeanNameGenerator();
                    for (Resource resource : resources) {
    //                    System.out.println(resource);
                        MetadataReader reader = factory.getMetadataReader(resource);
    //                    System.out.println("类名:" + reader.getClassMetadata().getClassName());
    //                    System.out.println("是否有@Component:" + reader.getAnnotationMetadata().hasAnnotation(Component.class.getName()));
    //                    System.out.println("是否有@Component派生注解:" + reader.getAnnotationMetadata().hasMetaAnnotation(Component.class.getName()));

                        if (reader.getAnnotationMetadata().hasAnnotation(Component.class.getName())
                                || reader.getAnnotationMetadata().hasMetaAnnotation(Component.class.getName())) {
                            AbstractBeanDefinition bd = BeanDefinitionBuilder
                                    .genericBeanDefinition(reader.getClassMetadata().getClassName())
                                    .getBeanDefinition();
                            if (configurableListableBeanFactory instanceof DefaultListableBeanFactory) {
                                DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) configurableListableBeanFactory;
                                String name = generator.generateBeanName(bd, beanFactory);
                                beanFactory.registerBeanDefinition(name, bd);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

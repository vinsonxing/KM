package com.study.spring.bean.testBeanConflict;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
https://blog.51cto.com/u_3631118/3124532
ConfigurationClassBeanDefinitionReader.isOverriddenByExistingDefinition() line 216 会返回false，因为这个bean的class和已经load的bean
不是同一个
如果是同一个，就会先Load后不再继续load,如果不是同一个，后面的覆盖前面的
 */
@Configuration
public class Application1 {
    @Bean(name = "pmAreasTService05")
    public PmAreasTService02 pmAreasTService02() {
        System.out.println("构造PmAreasTService02");
        return new PmAreasTService02();
    }
}

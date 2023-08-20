package com.study.spring.bean.BeanFactoryPostProcessor;

import com.alibaba.druid.pool.DruidDataSource;
import com.study.spring.bean.BeanFactoryPostProcessor.component.Bean1;
import com.study.spring.bean.BeanFactoryPostProcessor.component.Bean2;
import com.study.spring.bean.BeanFactoryPostProcessor.mapper.Mapper1;
import com.study.spring.bean.BeanFactoryPostProcessor.mapper.Mapper2;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.spring.study.bean.BeanFactoryPostProcessor.component")
public class Config {
    public Bean2 bean2(){
        return new Bean2();
    }
    @Bean(initMethod = "init")
    public Bean1 bean1() {
        System.out.println("调用 bean1()");
        return new Bean1();
    }

    @Bean
    public MapperFactoryBean<Mapper1> mapper1(SqlSessionFactory sqlSessionFactory){
        MapperFactoryBean<Mapper1> factory = new MapperFactoryBean<>(Mapper1.class);
        factory.setSqlSessionFactory(sqlSessionFactory);
        return factory;
    }

    @Bean
    public MapperFactoryBean<Mapper2> mapper2(SqlSessionFactory sqlSessionFactory){
        MapperFactoryBean<Mapper2> factory = new MapperFactoryBean<>(Mapper2.class);
        factory.setSqlSessionFactory(sqlSessionFactory);
        return factory;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean1(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return  sqlSessionFactoryBean;
    }
    @Bean
    public DruidDataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }


}

package com.study.spring.mvc.Exception;

import com.study.spring.mvc.MVCUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
@Configuration
public class WebConfig {
    @ControllerAdvice
    static class MyControllerAdvice{
        @ExceptionHandler
        @ResponseBody
        public Map<String, Object> handle(Exception e ) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", e.getMessage());
            return map;
        }
    }

    @Bean
    public ExceptionHandlerExceptionResolver resolver(){
        ExceptionHandlerExceptionResolver resolver = new ExceptionHandlerExceptionResolver();
        resolver.setMessageConverters(MVCUtil.getConverterList());
        return resolver;
    }
}

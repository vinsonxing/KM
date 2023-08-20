package com.study.spring.mvc.SimpleMappingAdapter;

import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigServletWebServerApplicationContext context = new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);

    }
}

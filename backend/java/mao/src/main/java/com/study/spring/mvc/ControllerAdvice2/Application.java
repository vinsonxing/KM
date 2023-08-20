package com.study.spring.mvc.ControllerAdvice2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite;
import org.springframework.web.servlet.mvc.method.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class Application {
    static Logger logger = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {

        logger.debug("this is debug");
        logger.info("this is info");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        ServletInvocableHandlerMethod handlerMethod = new ServletInvocableHandlerMethod(
//                context.getBean(WebConfig.MyController.class),
//                WebConfig.MyController.class.getMethod("user"));
//        )
    }

    public static HandlerMethodReturnValueHandlerComposite getReturnValueHandler() {
        List<HttpMessageConverter<?>> converterist = new ArrayList<>();
        converterist.add(new MappingJackson2HttpMessageConverter());
        HandlerMethodReturnValueHandlerComposite composite = new HandlerMethodReturnValueHandlerComposite();
        composite.addHandler(new ModelAndViewMethodReturnValueHandler());
        composite.addHandler(new ViewNameMethodReturnValueHandler());
        composite.addHandler(new ServletModelAttributeMethodProcessor(false));
        composite.addHandler(new HttpEntityMethodProcessor(converterist));
        composite.addHandler(new HttpHeadersReturnValueHandler());
        composite.addHandler(new RequestResponseBodyMethodProcessor(converterist));
        composite.addHandler(new ServletModelAttributeMethodProcessor(true));
        return composite;
    }
}

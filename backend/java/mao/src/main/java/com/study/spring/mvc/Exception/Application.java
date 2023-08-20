package com.study.spring.mvc.Exception;

import com.study.spring.mvc.MVCUtil;
import com.study.spring.mvc.ParameterResolver.Controller;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Application {
    public static void main(String[] args) throws NoSuchMethodException {
//        ExceptionHandlerExceptionResolver resolver = new ExceptionHandlerExceptionResolver();
//        resolver.setMessageConverters(MVCUtil.getConverterList());
//        resolver.afterPropertiesSet();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
        ExceptionHandlerExceptionResolver resolver = context.getBean(ExceptionHandlerExceptionResolver.class);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
//        HandlerMethod handlerMethod = new HandlerMethod(new Controller1(), Controller1.class.getMethod("foo"));
//        Exception e = new ArithmeticException("被零除");
//        resolver.resolveException(request, response, handlerMethod, e);
//        System.out.println(new String(response.getContentAsByteArray(), StandardCharsets.UTF_8));

//        HandlerMethod handlerMethod = new HandlerMethod(new Controller2(), Controller2.class.getMethod("foo"));
//        Exception e = new ArithmeticException("被零除");
//        ModelAndView mav = resolver.resolveException(request, response, handlerMethod, e);
//        System.out.println(mav.getModel());
//        System.out.println(mav.getViewName());

//        HandlerMethod handlerMethod = new HandlerMethod(new Controller3(), Controller3.class.getMethod("foo"));
//        Exception e = new Exception("e1", new RuntimeException("e2", new IOException("e3")));
//        resolver.resolveException(request, response, handlerMethod, e);
//        System.out.println(new String(response.getContentAsByteArray(), StandardCharsets.UTF_8));

        HandlerMethod handlerMethod = new HandlerMethod(new Controller5(), Controller5.class.getMethod("foo"));
        Exception e = new Exception("e1");
        resolver.resolveException(request, response, handlerMethod, e);
        System.out.println(new String(response.getContentAsByteArray(), StandardCharsets.UTF_8));
    }

    static class Controller1{
        public void foo(){
        }

        @ExceptionHandler
        @ResponseBody
        public Map<String, Object> handle(ArithmeticException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", e.getMessage());
            return map;
        }
    }
    static class Controller2{
        public void foo(){
        }

        @ExceptionHandler
        public ModelAndView handle(ArithmeticException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", e.getMessage());
            return new ModelAndView("test2", map);
        }
    }

    static class Controller3{
        public void foo(){
        }

        @ExceptionHandler
        @ResponseBody
        public Map<String, Object> handle(RuntimeException e3) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", e3.getMessage());
            return map;
        }
    }

    static class Controller4{
        public void foo(){
        }

        @ExceptionHandler
        @ResponseBody
        public Map<String, Object> handle(Exception e, HttpServletRequest request ) {
            System.out.println(request);
            Map<String, Object> map = new HashMap<>();
            map.put("error", e.getMessage());
            return map;
        }
    }

    static class Controller5 {
        public void foo() {
        }
    }
}

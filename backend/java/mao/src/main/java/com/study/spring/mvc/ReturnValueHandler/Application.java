package com.study.spring.mvc.ReturnValueHandler;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.*;
import org.springframework.web.util.UrlPathHelper;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);


        //1. 测试ModelAndView
//        test1(context);
        //2. 测试返回String
//        test2(context);
        //3. 测试返回ModelAttribute
//        test3(context);
        //5. 测试HttpEntity
//        test5(context);
        //6. 测试HttpHeader
        test6(context);
        //7. 测试responsebody
        test7(context);
    }

    private static void test1(AnnotationConfigApplicationContext context) throws Exception {
        Method method = Controller.class.getMethod("test1");
        Controller controller = new Controller();
        Object returnValue = method.invoke(controller);

        HandlerMethod handlerMethod = new HandlerMethod(controller, method);
        ModelAndViewContainer container = new ModelAndViewContainer();
        HandlerMethodReturnValueHandlerComposite composite = getReturnValueHandler();
        MethodParameter returnType = handlerMethod.getReturnType();
        if (composite.supportsReturnType(returnType)) {
            composite.handleReturnValue(returnValue, returnType, container, new ServletWebRequest(new MockHttpServletRequest()));
            System.out.println(container.getModel());
            System.out.println(container.getViewName());
        }
    }

    private static void test2(AnnotationConfigApplicationContext context) throws Exception {
        Method method = Controller.class.getMethod("test2");
        Controller controller = new Controller();
        Object returnValue = method.invoke(controller);

        HandlerMethod handlerMethod = new HandlerMethod(controller, method);
        ModelAndViewContainer container = new ModelAndViewContainer();
        HandlerMethodReturnValueHandlerComposite composite = getReturnValueHandler();
        MethodParameter returnType = handlerMethod.getReturnType();
        if (composite.supportsReturnType(returnType)) {
            composite.handleReturnValue(returnValue, returnType, container, new ServletWebRequest(new MockHttpServletRequest()));
            System.out.println(container.getModel());
            System.out.println(container.getViewName());
        }
    }

    private static void test3(AnnotationConfigApplicationContext context) throws Exception {
        Method method = Controller.class.getMethod("test3");
        Controller controller = new Controller();
        Object returnValue = method.invoke(controller);

        HandlerMethod handlerMethod = new HandlerMethod(controller, method);
        ModelAndViewContainer container = new ModelAndViewContainer();
        HandlerMethodReturnValueHandlerComposite composite = getReturnValueHandler();
        MethodParameter returnType = handlerMethod.getReturnType();
        if (composite.supportsReturnType(returnType)) {
            MockHttpServletRequest request = new MockHttpServletRequest();
            request.setRequestURI("/test3");
            UrlPathHelper.defaultInstance.resolveAndCacheLookupPath(request);
            composite.handleReturnValue(returnValue, returnType, container, new ServletWebRequest(request));
            System.out.println(container.getModel());
            System.out.println(container.getViewName());
        }
    }

    private static void test5(AnnotationConfigApplicationContext context) throws Exception {
        Method method = Controller.class.getMethod("test5");
        Controller controller = new Controller();
        Object returnValue = method.invoke(controller);

        HandlerMethod handlerMethod = new HandlerMethod(controller, method);
        ModelAndViewContainer container = new ModelAndViewContainer();
        HandlerMethodReturnValueHandlerComposite composite = getReturnValueHandler();
        MethodParameter returnType = handlerMethod.getReturnType();
        if (composite.supportsReturnType(returnType)) {
            MockHttpServletRequest request = new MockHttpServletRequest();
            MockHttpServletResponse response = new MockHttpServletResponse();
            composite.handleReturnValue(returnValue, returnType, container, new ServletWebRequest(request, response));
            System.out.println(container.getModel());
            System.out.println(container.getViewName());
            if (container.isRequestHandled()) {
                System.out.println(new String(response.getContentAsByteArray(), StandardCharsets.UTF_8));
            }
        }
    }

    private static void test6(AnnotationConfigApplicationContext context) throws Exception {
        Method method = Controller.class.getMethod("test6");
        Controller controller = new Controller();
        Object returnValue = method.invoke(controller);

        HandlerMethod handlerMethod = new HandlerMethod(controller, method);
        ModelAndViewContainer container = new ModelAndViewContainer();
        HandlerMethodReturnValueHandlerComposite composite = getReturnValueHandler();
        MethodParameter returnType = handlerMethod.getReturnType();
        if (composite.supportsReturnType(returnType)) {
            MockHttpServletRequest request = new MockHttpServletRequest();
            MockHttpServletResponse response = new MockHttpServletResponse();
            composite.handleReturnValue(returnValue, returnType, container, new ServletWebRequest(request, response));
            System.out.println(container.getModel());
            System.out.println(container.getViewName());
            if (container.isRequestHandled()) {
//                System.out.println(new String(response.getContentAsByteArray(), StandardCharsets.UTF_8));
                for (String name : response.getHeaderNames()) {
                    System.out.println(name + "=" + response.getHeader(name));
                }
            }
        }
    }

    private static void test7(AnnotationConfigApplicationContext context) throws Exception {
        Method method = Controller.class.getMethod("test7");
        Controller controller = new Controller();
        Object returnValue = method.invoke(controller);

        HandlerMethod handlerMethod = new HandlerMethod(controller, method);
        ModelAndViewContainer container = new ModelAndViewContainer();
        HandlerMethodReturnValueHandlerComposite composite = getReturnValueHandler();
        MethodParameter returnType = handlerMethod.getReturnType();
        if (composite.supportsReturnType(returnType)) {
            MockHttpServletRequest request = new MockHttpServletRequest();
            MockHttpServletResponse response = new MockHttpServletResponse();
            composite.handleReturnValue(returnValue, returnType, container, new ServletWebRequest(request, response));
            System.out.println(container.getModel());
            System.out.println(container.getViewName());
            if (container.isRequestHandled()) {
                System.out.println(new String(response.getContentAsByteArray(), StandardCharsets.UTF_8));
                for (String name : response.getHeaderNames()) {
                    System.out.println(name + "=" + response.getHeader(name));
                }
            }
        }
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

    static class Controller {
        public ModelAndView test1() {
            ModelAndView mav = new ModelAndView("view1");
            mav.addObject("name", "张三");
            return mav;
        }

        public String test2() {
            return "view2";
        }

        @ModelAttribute
        public User test3() {
            return new User("李四", 20);
        }

        public User test4() {
            return new User("王五", 30);
        }

        public HttpEntity<User> test5() {
            return new HttpEntity<>(new User("赵丽", 40));
        }

        public HttpHeaders test6() {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "text/html");
            return headers;
        }

        @ResponseBody
        public User test7() {
            return new User("钱七", 50);
        }
    }

    @Getter
    @Setter
    @ToString
    static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}

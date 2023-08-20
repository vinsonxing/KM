package com.study.spring.mvc.ParameterResolver;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockPart;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.support.DefaultDataBinderFactory;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExpressionValueMethodArgumentResolver;
import org.springframework.web.method.annotation.RequestHeaderMethodArgumentResolver;
import org.springframework.web.method.annotation.RequestParamMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
        DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();
        //准备测试request
        HttpServletRequest request = mockRequest();

        // 要点1. 控制器方法被封装为 HandlerMethod
        HandlerMethod handlerMethod = new HandlerMethod(new Controller(), Controller.class.getMethod("test", String.class, String.class, int.class, String.class, int.class, String.class, String.class, String.class, HttpServletRequest.class, Controller.User.class, Controller.User.class, Controller.User.class));

        // 要点2. 准备对象绑定与类型转换
        ServletRequestDataBinderFactory binderFactory = new ServletRequestDataBinderFactory(null, null);

        // 要点3：准备 ModelAndViewContainer 用来存储中间Model中间结果
        ModelAndViewContainer modelAndViewContainer = new ModelAndViewContainer();

        // 要点4： 准备每个参数值
        List<HttpMessageConverter<?>> converterist = new ArrayList<>();
        converterist.add(new MappingJackson2HttpMessageConverter());
        for (MethodParameter parameter : handlerMethod.getMethodParameters()) {
            HandlerMethodArgumentResolverComposite composite = new HandlerMethodArgumentResolverComposite();
            composite.addResolvers(
                    new RequestParamMethodArgumentResolver(beanFactory, false),// false 表示必须有这个注解
                    new PathVariableMethodArgumentResolver(),
                    new RequestHeaderMethodArgumentResolver(beanFactory),
                    new ServletCookieValueMethodArgumentResolver(beanFactory),
                    new ExpressionValueMethodArgumentResolver(beanFactory),
                    new ServletRequestMethodArgumentResolver(),
                    new ServletModelAttributeMethodProcessor(false), //必须有@ModelAttribute
                    new RequestResponseBodyMethodProcessor(converterist),
                    new ServletModelAttributeMethodProcessor(true),
                    new RequestParamMethodArgumentResolver(beanFactory, true)
            );
            String annotations = Arrays.stream(parameter.getParameterAnnotations()).map(a -> a.annotationType().getSimpleName()).collect(Collectors.joining(","));
            String str = annotations.length() > 0 ? "@" + annotations + " " : " ";
            parameter.initParameterNameDiscovery(new DefaultParameterNameDiscoverer());

            if (composite.supportsParameter(parameter)) {
                Object v = composite.resolveArgument(parameter, modelAndViewContainer, new ServletWebRequest(request), binderFactory);
//                System.out.println(v.getClass());
                System.out.println(str + parameter.getParameterType().getSimpleName() + " " + parameter.getParameterName() + " -> " + v);
                System.out.println("模型数据为 "+modelAndViewContainer);
            } else {
                System.out.println(str + parameter.getParameterType().getSimpleName() + " " + parameter.getParameterName());
            }
        }
    }

    private static HttpServletRequest mockRequest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("name1", "张三");
        request.setParameter("name2", "李四");
        request.addPart(new MockPart("file", "abc", "hello".getBytes(StandardCharsets.UTF_8)));
        ///////以下两行代码本来是HandlerMapper做的////////
        Map<String, String> map = new AntPathMatcher().extractUriTemplateVariables("/test/{id}", "/test/123");
        //System.out.println(map);
        request.setAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, map);
        //////////////////////////////////////////////////
        request.setContentType("application/json");
        request.setCookies(new Cookie("token", "123456"));
        request.setParameter("name", "张三");
        request.setParameter("age", "18");
        Map<String, String> jmap = new HashMap<>();
        jmap.put("name", "李四");
        jmap.put("age", "20");
        request.setContent(JSON.toJSONString(jmap).getBytes(StandardCharsets.UTF_8));
        return new StandardServletMultipartResolver().resolveMultipart(request);
    }
}

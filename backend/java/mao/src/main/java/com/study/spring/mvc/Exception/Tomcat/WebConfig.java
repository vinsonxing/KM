package com.study.spring.mvc.Exception.Tomcat;

import com.study.spring.mvc.MVCUtil;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistrarBeanPostProcessor;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.BeanNameViewResolver;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebConfig {
    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    @Bean
    public DispatcherServletRegistrationBean dispatcherServletRegistrationBean() {
        return new DispatcherServletRegistrationBean(dispatcherServlet(), "/");
    }

    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        return new RequestMappingHandlerMapping();
    }

    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
        adapter.setMessageConverters(MVCUtil.getConverterList());
        return adapter;
    }

    @Bean //修改了tomcat服务器默认错误地址
    public ErrorPageRegistrar errorPageRegistrar() {
        return webserverFactory -> webserverFactory.addErrorPages(new ErrorPage("/error"));
    }

    @Bean
    public ErrorPageRegistrarBeanPostProcessor errorPageRegistrarBeanPostProcessor() {
        return new ErrorPageRegistrarBeanPostProcessor();
    }

    @Controller
    public static class MyController {
        @RequestMapping("/test")
        public ModelAndView test() {
            int i = 1 / 0;
            return null;
        }
       /* @RequestMapping("/error")
        @ResponseBody
        public Map<String, Object> error(HttpServletRequest request){
            Map<String, Object> map = new HashMap<>();
            map.put("error", ((Throwable)request.getAttribute(RequestDispatcher.ERROR_EXCEPTION)).getMessage());
            return map;
        }*/

        @Bean
        public BasicErrorController basicErrorController() {
            ErrorProperties errorProperties = new ErrorProperties();
            errorProperties.setIncludeException(true);
            return new BasicErrorController(new DefaultErrorAttributes(), errorProperties);
        }
        @Bean
        public View error(){
            return new View() {
                @Override
                public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
                    System.out.println(model);
                    response.setContentType("text/html;charset=utf-8");
                    response.getWriter().print("<h3>" +
                            "服务器内部错误" +
                            "</h3>");
                }
            };
        }
        @Bean
        public ViewResolver viewResolver(){
            return new BeanNameViewResolver();
        }
    }
}

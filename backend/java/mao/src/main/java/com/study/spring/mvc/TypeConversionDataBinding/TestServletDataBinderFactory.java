package com.study.spring.mvc.TypeConversionDataBinding;

import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestParameterPropertyValues;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.InvocableHandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ServletRequestDataBinderFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestServletDataBinderFactory {
    public static void main(String[] args) throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("birthday", "1999|01|02");
        request.setParameter("address.name", "西安");
        User target = new User();
        //1. 用工厂，无转换功能
//        ServletRequestDataBinderFactory factory = new ServletRequestDataBinderFactory(null, null);

        //2. 用InitBinder转换
//        InvocableHandlerMethod method = new InvocableHandlerMethod(new MyController(), MyController.class.getMethod("aaa",WebDataBinder.class));
//        List<InvocableHandlerMethod> list = new ArrayList<>();
//        list.add(method);
//        ServletRequestDataBinderFactory factory = new ServletRequestDataBinderFactory(list, null);

        //3. 用ConversionService转换
//        FormattingConversionService service = new FormattingConversionService();
//        service.addFormatter(new MyDateFormatter("用ConversionService 方式扩展转换功能"));
//        ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
//        initializer.setConversionService(service);
//        ServletRequestDataBinderFactory factory = new ServletRequestDataBinderFactory(null, initializer);

        //4. 同时加了@InitBinder和ConversionService
//        InvocableHandlerMethod method = new InvocableHandlerMethod(new MyController(), MyController.class.getMethod("aaa",WebDataBinder.class));
//        FormattingConversionService service = new FormattingConversionService();
//        service.addFormatter(new MyDateFormatter("用ConversionService 方式扩展转换功能"));
//        ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
//        initializer.setConversionService(service);
//        List<InvocableHandlerMethod> list = new ArrayList<>();
//        list.add(method);
//        ServletRequestDataBinderFactory factory = new ServletRequestDataBinderFactory(list, initializer);

        //5. 使用默认ConversionService
        ApplicationConversionService service = new ApplicationConversionService();
        ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
        initializer.setConversionService(service);
        ServletRequestDataBinderFactory factory = new ServletRequestDataBinderFactory(null, initializer);

        WebDataBinder binder = factory.createBinder(new ServletWebRequest(request), target, "user");
        binder.bind(new ServletRequestParameterPropertyValues(request));
        System.out.println(target);
    }
    @Controller
    static class MyController {
        @InitBinder
        public void aaa(WebDataBinder webDataBinder) {
            //扩展DataBinder 的转换器
            webDataBinder.addCustomFormatter(new MyDateFormatter("用 @InitBinder扩展"));
        }
    }

    public static class User {
        @DateTimeFormat(pattern="yyyy|MM|dd")
        private Date birthday;
        private Address address;

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "User{" +
                    "birthday=" + birthday +
                    ", address=" + address.name +
                    '}';
        }
    }

    static class Address {
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

package com.study.spring.mvc.MessageConverter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.mock.http.MockHttpInputMessage;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    如何选择MediaType
            - 首先看 @RequestMapping 上有没有指定
            - 其次看 request的Accept头有没有指定
            - 最后按 MessageConverter的顺序 谁先谁转换
 */
public class Application {
    public static void main(String[] args) throws IOException, NoSuchMethodException, HttpMediaTypeNotAcceptableException {
//        test1();
//        test2();
//        test3();
        test4();
    }
    public static void test1() throws IOException {
        MockHttpOutputMessage message = new MockHttpOutputMessage();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        if (converter.canWrite(User.class, MediaType.APPLICATION_JSON)) {
            converter.write(new User("张三",18), MediaType.APPLICATION_JSON, message);
            System.out.println(message.getBodyAsString());
        }
    }

    public static void test2() throws IOException {
        MockHttpOutputMessage message = new MockHttpOutputMessage();
        MappingJackson2XmlHttpMessageConverter converter = new MappingJackson2XmlHttpMessageConverter();
        if (converter.canWrite(User.class, MediaType.APPLICATION_XML)) {
            converter.write(new User("张三",18), MediaType.APPLICATION_XML, message);
            System.out.println(message.getBodyAsString());
        }
    }

    public static void test3() throws IOException {
        Map<String, String> jmap = new HashMap<>();
        jmap.put("name", "李四");
        jmap.put("age", "20");
        MockHttpInputMessage message = new MockHttpInputMessage(JSON.toJSONString(jmap).getBytes(StandardCharsets.UTF_8));
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        if (converter.canRead(User.class, MediaType.APPLICATION_JSON)) {
           Object read = converter.read(User.class, message);
            System.out.println(read);
        }
    }

    public static void test4() throws IOException, NoSuchMethodException, HttpMediaTypeNotAcceptableException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.addHeader("Accept", "application/xml");
        response.setContentType("application/json");

        ServletWebRequest webRequest = new ServletWebRequest(request, response);
        List<HttpMessageConverter<?>> converterist = new ArrayList<>();
        converterist.add(new MappingJackson2HttpMessageConverter());
        converterist.add(new MappingJackson2XmlHttpMessageConverter());
        RequestResponseBodyMethodProcessor processor = new RequestResponseBodyMethodProcessor(converterist);
        processor.handleReturnValue(new User("张三",18),
                                    new MethodParameter(Application.class.getMethod("user"), -1),
                                    new ModelAndViewContainer(),
                                    webRequest);
        System.out.println(new String(response.getContentAsByteArray(), StandardCharsets.UTF_8));
    }

    @ResponseBody
    public User user(){
        return null;
    }
    public static class User{
        private String name;
        private int age;

        @JsonCreator
        public User(@JsonProperty("name") String name, @JsonProperty("age") int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}

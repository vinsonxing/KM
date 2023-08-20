package com.study.spring.mvc.ParameterResolver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public class Controller {
    public void test(
            @RequestParam("name1") String name1, //name1="张三"， URL 或者表单提交
            String name2,                         //name2="李四"
            @RequestParam("age") int age,        //age=18
            @RequestParam(name = "home1", defaultValue = "${JAVA_HOME}") String home1,
            //@RequestParam("file") MultipartFile file,   //上传文件
            @PathVariable("id") int id,          //   /test/123  /test/{id}
            @RequestHeader("Content-Type") String header,
            @CookieValue("token") String token,
            @Value("${JAVA_HOME}") String home2,
            HttpServletRequest request,    //解析特殊类型参数 request response session
            @ModelAttribute("abc") User user1,     // name=zhang&&age=18
            User user2,                     // name=zhang&&age=18
            @RequestBody User user3         // json
            ) {

    }
    static class User{
        private String name;
        private int age;
        public User(){

        }
        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
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

package com.study.spring.mvc.DispatcherServelet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.yaml.snakeyaml.Yaml;

@Controller
public class Controller1 {
    @GetMapping("/test1")
    public ModelAndView test1() {
        System.out.println("test1()被调用");
        return null;
    }

    @PostMapping("/test2")
    public ModelAndView test2(@RequestParam("name") String name) {
        System.out.println("test2()被调用，参数是 " + name);
        return null;
    }

    @PutMapping("/test3")
    public ModelAndView test3(@Token String token) {
        System.out.println("test3()被调用， 令牌是 " + token);
        return null;
    }

    @RequestMapping("/test4")
//    @ResponseBody
    @Yml
    public User test4() {
        System.out.println("test4()被调用");
        return new User("张三",18);
    }

    public static class User {
        String name;
        int age;

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
    }

    public static void main(String[] args) {
        String str = new Yaml().dump(new User("张三",18));
        System.out.println(str);
    }
}

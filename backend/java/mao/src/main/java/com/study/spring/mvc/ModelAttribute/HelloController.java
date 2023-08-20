package com.study.spring.mvc.ModelAttribute;

import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
//https://blog.51cto.com/u_3631118/3121661
@RestController
@RequestMapping
//@SessionAttributes(names = {"name", "age"}, types = Person.class)
@SessionAttributes("myPersonAttr")
public class HelloController {

    @ModelAttribute("myPersonAttr")
    public Person personModelAttr() {
        return new Person("非功能方法", 50);
    }

    @GetMapping("/testModelAttr")
    public void testModelAttr(@ModelAttribute("myPersonAttr") Person person, ModelMap modelMap) {
        System.out.println(modelMap.get("myPersonAttr")); // 若上面注解没有指定value值，就是类名首字母小写
        System.out.println(modelMap.get("myPersonAttr"));
        //System.out.println(httpSession.getAttribute("person"));
    }

    @GetMapping("/testModelAttr1")
    public void testModelAttr1(HttpSession httpSession, ModelMap modelMap) {
        System.out.println(modelMap.get("myPersonAttr")); // 若上面注解没有指定value值，就是类名首字母小写
        System.out.println(httpSession.getAttribute("myPersonAttr"));
    }

    @GetMapping("/testModelAttr2")
    public void testModelAttr2(@ModelAttribute Person person, HttpSession httpSession, ModelMap modelMap) {
        System.out.println(modelMap.get("myPersonAttr")); // 若上面注解没有指定value值，就是类名首字母小写
        System.out.println(httpSession.getAttribute("myPersonAttr"));
    }
}


package com.vinson.study.java8.methodreference;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Person {
    String name;

    public void eat() {
        System.out.println("eta method run:");
    }

    public static void main(String[] args) {
//      实例方法引用
        Arrays.asList(new Person("vv")).forEach(Person::eat);
//      equals to:
//      Arrays.asList(new Person("vv")).forEach(p -> p.eat());
        System.out.println("------------------------------------------------------------------");

//        静态方法引用
        Arrays.asList(new Person("vv")).forEach(System.out::println);
//        Arrays.asList(new Person("vv")).forEach(p -> System.out.println(p));
        System.out.println("------------------------------------------------------------------");
//        构造器引用
        String[] names = {"p1", "p2", "p3", "p4", "p5"};
        Arrays.stream(names).map(Person::new).forEach(System.out::println);

        System.out.println("------------------------------------------------------------------");
//        this or super 方法引用, 与实例方法引用的相同，都有一个实例对象p， 不同是需要p作为引用方法的入参 ！！！！
        Person p = new Person("");
        p.printName();

    }

    public void printName() {
        String[] names = {"p1", "p2", "p3", "p4", "p5"};
        Arrays.stream(names).map(Person::new).forEach(this::consumePerson);
//        Arrays.stream(names).map(Person::new).forEach(p -> this.consumePerson(p));
    }

    public void consumePerson(Person p) {
        System.out.println(p);
    }

}



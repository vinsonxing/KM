package com.vinson.study.java8.methodreference;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
    String name;
    String job;

    public void eat() {
        System.out.println("eta method run:");
    }

    public static void main(String[] args) {
        Arrays.asList(new Person("vv","mm")).forEach(Person::eat);
    }
}



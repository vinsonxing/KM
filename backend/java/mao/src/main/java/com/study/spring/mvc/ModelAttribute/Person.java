package com.study.spring.mvc.ModelAttribute;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Person {
    private String name;
    private Integer age;

    public Person() {
    }

    public Person(String myName, int myAge) {
        this.name = myName;
        this.age = myAge;
    }
}


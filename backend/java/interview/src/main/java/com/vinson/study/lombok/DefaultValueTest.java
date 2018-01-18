package com.vinson.study.lombok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class DefaultValueTest {
    @Builder.Default // need to update the IDE (eclipse) reference to lombok, and restart IDE.
    private long created = System.currentTimeMillis();
    private String name;
    private int age;

    public static void main(String[] args) {
        DefaultValueTest be = DefaultValueTest.builder().name("asdasd").build();
        System.out.println(be.getCreated());

    }
}

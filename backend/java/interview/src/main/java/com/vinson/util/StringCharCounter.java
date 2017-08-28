package com.vinson.util;

import org.springframework.util.StringUtils;

import com.google.common.base.CharMatcher;

public class StringCharCounter {
    public static void main(String[] args) {
        System.out.println(StringUtils.countOccurrencesOf("asdasd,sdsd,asd,qwesd", ",")); // 3
        System.out.println(CharMatcher.is(',').countIn("asdasd,sdsd,asd,qwesd")); // 3
    }
}

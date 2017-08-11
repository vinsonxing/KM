package com.vinson.study.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListTest {

   public static void main(String[] args) {
        List<String> tt = Arrays.asList("Hello", "world");
        System.out.println(tt.stream().map(t -> {return t.split("");}).collect(Collectors.toList()));
        //[[Ljava.lang.String;@4eec7777, [Ljava.lang.String;@3b07d329]

        System.out.println(tt.stream().map(t -> {return t.split("");}).flatMap(Arrays::stream).collect(Collectors.toList()));
        // [H, e, l, l, o, w, o, r, l, d]

        System.out.println(tt.stream().map(t -> {return t.split("");}).flatMap(Arrays::stream).distinct().collect(Collectors.toList()));
        // [H, e, l, o, w, r, d]
        
        
        
   }
}

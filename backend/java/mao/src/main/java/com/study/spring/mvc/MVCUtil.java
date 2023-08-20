package com.study.spring.mvc;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

public class MVCUtil {
    public static List<HttpMessageConverter<?>> getConverterList(){
        List<HttpMessageConverter<?>> converterist = new ArrayList<>();
        converterist.add(new MappingJackson2HttpMessageConverter());
        return converterist;
    }

}

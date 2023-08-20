package com.study.spring.bean.testImport;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class BaseConfig {
    @Bean("mouse")
    public Mouse getMouse() {
        return new Mouse();
    }
}

class Mouse {

}




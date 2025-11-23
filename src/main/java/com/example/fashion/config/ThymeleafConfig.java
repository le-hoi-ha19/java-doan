package com.example.fashion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import com.example.fashion.utils.ImageUtils;

@Configuration
public class ThymeleafConfig {
    
    @Bean
    public ImageUtils imageUtils() {
        return new ImageUtils();
    }
    
    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }
}

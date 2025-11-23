package com.example.fashion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.fashion.utils.ImageUtils;

@Configuration
public class ThymeleafConfig {
    
    @Bean
    public ImageUtils imageUtils() {
        return new ImageUtils();
    }
}

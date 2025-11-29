package com.example.fashion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.TimeZone;

import com.example.fashion.services.StorageService;

@SpringBootApplication
public class FashionApplication {

	private static final Logger logger = LoggerFactory.getLogger(FashionApplication.class);

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
		SpringApplication.run(FashionApplication.class, args);
		logger.info("Fashion đã chạy xong>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		logger.info("Truy cập url trên trình duyệt: http://localhost:9090");
	}

	@Bean
	CommandLineRunner init(StorageService storageService){
		return (args) -> {
			storageService.init();
			logger.info("Storage service initialized.");
		};
	}
}

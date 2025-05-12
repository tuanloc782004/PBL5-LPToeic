package com.pbl5.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/otp").setViewName("otp");
		registry.addViewController("/user/account/change-password")
				.setViewName("user/account/change-password");

	}

}

// MvcConfig này dùng để định tuyến (mapping) một số URL đến view mà không cần tạo controller riêng.
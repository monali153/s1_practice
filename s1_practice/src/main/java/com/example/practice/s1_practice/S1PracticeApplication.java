package com.example.practice.s1_practice;

import com.example.practice.s1_practice.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class S1PracticeApplication {

	public static void main(String[] args) {

		SpringApplication.run(S1PracticeApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean filterBean(){

		FilterRegistrationBean registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/custdata/v1/*");
		return registrationBean;
	}


}


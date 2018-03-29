package com.chessa.example.mongorest.RestMongoReadhbase;

import com.chessa.example.mongorest.RestMongoReadhbase.config.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class RestMongoReadhbaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestMongoReadhbaseApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean corsFilterRegistration(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean(new CorsFilter());
		registrationBean.setName("CORS Filter");
		registrationBean.addUrlPatterns("/*");
		registrationBean.setOrder(1);
		return registrationBean;
	}
}

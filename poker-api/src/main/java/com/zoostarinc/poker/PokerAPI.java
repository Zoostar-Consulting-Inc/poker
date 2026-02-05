package com.zoostarinc.poker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.Generated;

@Generated
@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "net.zoostar", "com.zoostarinc" })
public class PokerAPI extends SpringBootServletInitializer implements WebMvcConfigurer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PokerAPI.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(PokerAPI.class, args);
	}

}

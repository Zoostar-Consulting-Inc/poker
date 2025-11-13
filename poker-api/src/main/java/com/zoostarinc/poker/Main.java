package com.zoostarinc.poker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.Generated;

@Generated
@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "net.zoostar", "com.zoostarinc" })
public class Main implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	OpenAPI openAPI() {
		return new OpenAPI().info(new Info().title("Poker API")
				.description("This API provides operations for Poker."));
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/swagger-ui/index.html");
	}

}

package com.zoostarinc.poker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "net.zoostar", "com.zoostarinc" })
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	OpenAPI openAPI() {
		return new OpenAPI().info(new Info().title("Employee API")
				.description("This API provides CRUD operations for Employees with OAuth2 security."));
	}

}

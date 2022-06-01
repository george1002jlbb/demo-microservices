package com.example.demoservicesclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DemoServicesClientApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoServicesClientApplication.class, args);

	}

}

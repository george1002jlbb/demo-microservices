package com.example.demoservicesproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DemoServicesProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoServicesProductApplication.class, args);
	}

}

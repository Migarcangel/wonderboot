package com.project.wonderboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class WonderbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(WonderbootApplication.class, args);
	}

}

package com.github.smsilva.wasp.demo.springpropertiesdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringPropertiesDemoApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringPropertiesDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringPropertiesDemoApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(DemoConfig config) {
		return args -> {
			LOGGER.info("Hi");
			LOGGER.info("config.url = {}}", config.getUrl());
		};
	}

}

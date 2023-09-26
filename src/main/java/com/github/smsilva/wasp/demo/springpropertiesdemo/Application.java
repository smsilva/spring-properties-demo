package com.github.smsilva.wasp.demo.springpropertiesdemo;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClientRequest;

@SpringBootApplication
@EnableConfigurationProperties
public class Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(DemoConfig demoConfig, WebClient defaultWebClient) {
		return args -> extracted(demoConfig, defaultWebClient);
	}

	private void extracted(DemoConfig demoConfig, WebClient defaultWebClient) {
		LOGGER.info("config.url = {}}", demoConfig.getUrl());
		
		String body = defaultWebClient.get()
			.uri("/delay/1")
			.httpRequest(httpRequest -> {
				HttpClientRequest reactorRequest = httpRequest.getNativeRequest();
				reactorRequest.responseTimeout(Duration.ofSeconds(5));
			})
			.accept(MediaType.APPLICATION_JSON)
			.exchangeToMono(response -> {
				if (response.statusCode().equals(HttpStatus.OK)) {
					return response.bodyToMono(String.class);
				}
				else {
					return response.createError();
				}
			})
			.block();

		LOGGER.info("{}", body);
	}

}

package com.github.smsilva.wasp.demo.springpropertiesdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties
public class AppConfig {

    @Autowired
    AppProperties appProperties;

    @Bean
    WebClient defaultWebClient() {
        return WebClient.builder()
            .baseUrl(appProperties.getUrl())
            .build();
    }
    
}

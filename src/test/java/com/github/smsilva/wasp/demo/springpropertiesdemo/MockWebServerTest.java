package com.github.smsilva.wasp.demo.springpropertiesdemo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties
@TestPropertySource("classpath:application-test.properties")
@TestInstance(Lifecycle.PER_CLASS)
public class MockWebServerTest {

    MockWebServer mockBackEnd;
    WebClient defaultWebClient;

    @BeforeEach
    void beforeClass() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();

        String baseUrl = String.format("http://localhost:%s", mockBackEnd.getPort());
    
        defaultWebClient = WebClient.builder()
            .baseUrl(baseUrl)
            .build();
    }

    @AfterEach
    void afterClass() throws IOException {
        mockBackEnd.shutdown();
    }

    @Test
    void test() throws InterruptedException {
        mockBackEnd.enqueue(new MockResponse()
            .setBody("{'id':1}")
            .addHeader("Content-Type", "application/json")
            .setResponseCode(HttpStatus.OK.value()));

        ResponseEntity<Void> response = defaultWebClient.get()
            .uri("/get")
            .retrieve()
            .toBodilessEntity()
            .block();

        assertTrue(response.getStatusCode() == HttpStatusCode.valueOf(200));

        RecordedRequest recordedRequest = mockBackEnd.takeRequest();
        assertEquals("/get", recordedRequest.getPath());
    }

}

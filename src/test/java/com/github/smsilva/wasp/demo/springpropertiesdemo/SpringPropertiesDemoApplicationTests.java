package com.github.smsilva.wasp.demo.springpropertiesdemo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringPropertiesDemoApplicationTests {

	@Autowired
	DemoConfig demoConfig;

	@Test
	void contextLoads() {
		assertNotNull(demoConfig);
		assertNotNull(demoConfig.getUrl());
		assertTrue(demoConfig.getUrl().contains("httpbin"));
	}

}

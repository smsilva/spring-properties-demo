package com.github.smsilva.wasp.demo.springpropertiesdemo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application-test.properties")
@ContextConfiguration(classes = DemoConfig.class)
public class DemoConfigTest {

    @Autowired
    DemoConfig demoConfig;

    @Test
    void test() {
        assertNotNull(demoConfig);
        assertNotNull(demoConfig.getUrl());
        assertTrue(demoConfig.getUrl().contains("localhost"));        
    }
    
}

package com.github.smsilva.wasp.demo.springpropertiesdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfig {

	private String url;

	public DemoConfig(@Value("${app.integration.echo.url}") String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
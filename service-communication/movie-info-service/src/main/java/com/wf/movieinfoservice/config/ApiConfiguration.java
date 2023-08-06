package com.wf.movieinfoservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "movie-service")
@Getter
@Setter
public class ApiConfiguration {
	
	private String apiKey;

}

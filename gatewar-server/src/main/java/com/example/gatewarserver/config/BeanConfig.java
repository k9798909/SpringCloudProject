package com.example.gatewarserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.gatewarserver.utils.AuthJwtUtils;

@Configuration
public class BeanConfig {
	@Value("${jwt.key}")
	private String key;
	
	@Bean 
	public AuthJwtUtils authJwtUtils() {
		return new AuthJwtUtils(key);
	}
}

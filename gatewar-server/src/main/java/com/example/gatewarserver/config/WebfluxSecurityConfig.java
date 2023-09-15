package com.example.gatewarserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.example.gatewarserver.config.auth.JwtAuthenticationManager;
import com.example.gatewarserver.config.auth.JwtSecurityContextRepository;
import com.example.gatewarserver.utils.AuthJwtUtils;

import reactor.core.publisher.Mono;

@Configuration
public class WebfluxSecurityConfig {
	private AuthJwtUtils jwtUtils;

	public WebfluxSecurityConfig(AuthJwtUtils jwtUtils) {
		super();
		this.jwtUtils = jwtUtils;
	}

	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http,
			JwtAuthenticationManager jwtAuthenticationManager,
			JwtSecurityContextRepository jwtSecurityContextRepository) {
		// @formatter:off
	    http.exceptionHandling()
	        .authenticationEntryPoint((swe, e) ->
	            Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR))
	        ).accessDeniedHandler((swe, e) ->
	            Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN))
	        ).and()
	        .csrf().disable()
	        .formLogin().disable()
	        .httpBasic().disable()
	        .authenticationManager(jwtAuthenticationManager)// 身分驗證
            .securityContextRepository(jwtSecurityContextRepository) // 使用者發出新的HTTP請求時執行
            .authorizeExchange()
            .pathMatchers("/login","/tokenVerify").permitAll()
            .pathMatchers(HttpMethod.GET,"/product-service/product","/product-service/product/*","/product-service/product/img/*").permitAll()
            .pathMatchers(HttpMethod.POST,"/users/add").permitAll()
            .anyExchange().authenticated()
	        ;
	    // @formatter:on
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public JwtAuthenticationManager jwtAuthenticationManager() {
		return new JwtAuthenticationManager(jwtUtils);
	}

	@Bean
	public JwtSecurityContextRepository jwtSecurityContextRepository(
			JwtAuthenticationManager jwtAuthenticationManager) {
		return new JwtSecurityContextRepository(jwtUtils, jwtAuthenticationManager);
	}

}

package com.example.gatewarserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.example.gatewarserver.config.auth.JwtAuthenticationManager;
import com.example.gatewarserver.config.auth.JwtSecurityContextRepository;
import com.example.gatewarserver.repository.UserRepository;
import com.example.gatewarserver.utils.AuthJwtUtils;

import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class WebfluxSecurityConfig {

	private UserRepository userRepository;
	private AuthJwtUtils jwtUtils;

	public WebfluxSecurityConfig(UserRepository userRepository, AuthJwtUtils jwtUtils) {
		super();
		this.userRepository = userRepository;
		this.jwtUtils = jwtUtils;
	}

	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http,JwtAuthenticationManager jwtAuthenticationManager,JwtSecurityContextRepository jwtSecurityContextRepository) {
		// @formatter:off
	    http.exceptionHandling()
	        .authenticationEntryPoint((swe, e) -> 
	            Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED))
	        ).accessDeniedHandler((swe, e) -> 
	            Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN))
	        ).and()
	        .csrf().disable()
	        .formLogin().disable()
	        .httpBasic().disable()
	        .authenticationManager(jwtAuthenticationManager)
            .securityContextRepository(jwtSecurityContextRepository)
            .authorizeExchange()
            .pathMatchers("/product-service/product","/login").permitAll()
            .anyExchange().authenticated()
            .and()
	        .oauth2Login()
	        .and()
	        .formLogin().disable()
	        ;
	    // @formatter:on
		return http.build();
	}

	@Bean
	public ReactiveClientRegistrationRepository clientRegistrationRepository() {
		return new InMemoryReactiveClientRegistrationRepository(this.googleClientRegistration());
	}

	private ClientRegistration googleClientRegistration() {
		// @formatter:off
		return ClientRegistration.withRegistrationId("google")
				.clientId("")
				.clientSecret("")
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.redirectUri("http://localhost:8080/login/oauth2/code/google")
				.scope("openid", "profile", "email", "address", "phone")
				.authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
				.tokenUri("https://www.googleapis.com/oauth2/v4/token")
				.userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
				.userNameAttributeName(IdTokenClaimNames.SUB)
				.jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
				.clientName("Google")
				.build();
		// @formatter:on
	}

	@Bean
	public ReactiveUserDetailsService userDetailsService() {
		return (username) -> userRepository.findByUsername(username);
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
	public JwtSecurityContextRepository jwtSecurityContextRepository(JwtAuthenticationManager jwtAuthenticationManager) {
		return new JwtSecurityContextRepository(jwtUtils, jwtAuthenticationManager);
	}

}

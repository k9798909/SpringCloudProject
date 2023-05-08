package com.example.gatewarserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.gatewarserver.model.Users;
import com.example.gatewarserver.repository.UserRepository;

@SpringBootApplication
@EnableDiscoveryClient
@EnableWebFluxSecurity
public class GatewarServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewarServerApplication.class, args);
	}

	@Value("${gateway.uri.product-service}")
	private String proudctServiceUri;
	@Value("${gateway.uri.cart-service}")
	private String cartServiceUri;

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		// @formatter:off
		return builder.routes()
				.route("product-service", r -> r.path("/product-service/**").uri(proudctServiceUri))
				.route("cart-service", r -> r.path("/cart-service/**").uri(cartServiceUri))
				.build();
		// @formatter:on
	}

	@Bean
	public CommandLineRunner start(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			Users users = new Users("test", passwordEncoder.encode("test"));
			users.setName("測試先生");
			userRepository.deleteAll().log().subscribe();
			userRepository.save(users).log().subscribe();
			userRepository.findAll().log().subscribe();
		};
	}

}

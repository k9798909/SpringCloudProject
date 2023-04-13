package com.example.gatewarserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
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
		return builder.routes()
				.route("product-service", r -> r.path("/product-service/**").uri(proudctServiceUri))
				.route("cart-service", r -> r.path("/cart-service/**").uri(cartServiceUri))
				.build();
	}

}

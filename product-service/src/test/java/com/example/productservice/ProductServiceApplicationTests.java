package com.example.productservice;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.example.productservice.services.ProductService;

@SpringBootTest(properties = "eureka.client.enabled=false")
@TestPropertySource(locations = "classpath:application-test.properties")
class ProductServiceApplicationTests {

	@Autowired
	ProductService productService;

	@Test
	void contextLoads() {
	}

}

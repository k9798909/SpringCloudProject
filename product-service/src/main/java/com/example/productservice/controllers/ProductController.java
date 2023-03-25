package com.example.productservice.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/")
	public List<ProductDto> getAll() {
		return productService.findAll();
	}
}

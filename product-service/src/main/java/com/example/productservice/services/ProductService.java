package com.example.productservice.services;

import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;

@Service
public class ProductService {
	private ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public List<ProductDto>  findAll() {
		return productRepository.findAll().stream().map(conver()).toList();
	}
	
	private Function<Product, ProductDto> conver() {
		return product -> new ProductDto(
				product.getId(), 
				product.getName(), 
				product.getDescription(), 
				product.getPrice(),
				product.getQuantity()
		);
	}
}

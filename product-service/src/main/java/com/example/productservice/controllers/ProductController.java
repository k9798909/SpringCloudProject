package com.example.productservice.controllers;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping
	public ResponseEntity<List<ProductDto>> getAll() {
		return ResponseEntity.ok(productService.findAll());
	}

	@GetMapping("/{proId}")
	public ResponseEntity<ProductDto> findBy(@PathVariable("proId") String proId) {
		return new ResponseEntity<>(productService.findByProId(proId), HttpStatus.OK);
	}

	@GetMapping("/img/{proId}")
	public ResponseEntity<byte[]> getAll(@PathVariable("proId") String proId) {
		byte[] img = productService.findImgByProId(proId);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		headers.setContentLength(img.length);
		return new ResponseEntity<>(img, headers, HttpStatus.OK);
	}
}

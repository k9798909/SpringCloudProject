package com.example.productservice.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.model.Product;
import com.example.productservice.model.ProductImage;
import com.example.productservice.repository.ProductImageRepository;
import com.example.productservice.repository.ProductRepository;

@Service
public class ProductService {
	private ProductRepository productRepository;
	private ProductImageRepository productImageRepository;

	public ProductService(ProductRepository productRepository, ProductImageRepository productImageRepository) {
		this.productRepository = productRepository;
		this.productImageRepository = productImageRepository;
	}

	public List<ProductDto> findAll() {
		return productRepository.findAll().stream().map(conver()).toList();
	}

	public ProductDto findByProId(String proId) {
		return productRepository.findById(proId).stream().map(conver()).findAny()
				.orElseThrow(() -> new NullPointerException("查無對應商品"));
	}

	public byte[] findImgByProId(String proId) {
		ProductImage proImg = new ProductImage();
		proImg.setProductId(proId);
		Example<ProductImage> ex = Example.of(proImg);
		Optional<ProductImage> queryProImg = productImageRepository.findOne(ex);
		return queryProImg.map(ProductImage::getImage).orElseThrow(() -> new RuntimeException("查無圖片"));
	}

	private Function<Product, ProductDto> conver() {
		return product -> new ProductDto(product.getId(), product.getName(), product.getDescription(),
				product.getPrice(), product.getQuantity());
	}
}

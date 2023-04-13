package com.example.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.productservice.model.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

}

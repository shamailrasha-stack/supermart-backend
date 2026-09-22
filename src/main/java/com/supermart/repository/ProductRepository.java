package com.supermart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supermart.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByCategoryId(Long categoryId);

    Optional<Product> findBySkuIgnoreCase(String sku);

    Optional<Product> findByBarcodeIgnoreCase(String barcode);
}
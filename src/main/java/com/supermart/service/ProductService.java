package com.supermart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermart.entity.Product;
import com.supermart.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {

        Product existing = productRepository.findById(id).orElse(null);

        if (existing == null) {
            return null;
        }

        existing.setName(product.getName());
        existing.setSku(product.getSku());
        existing.setBarcode(product.getBarcode());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setLowStockThreshold(product.getLowStockThreshold());
        existing.setActive(product.isActive());
        existing.setCategory(product.getCategory());

        return productRepository.save(existing);
    }

    public List<Product> searchProducts(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public Product toggleStatus(Long id) {

        Product product = productRepository.findById(id).orElse(null);

        if (product != null) {
            product.setActive(!product.isActive());
            return productRepository.save(product);
        }

        return null;
    }
}
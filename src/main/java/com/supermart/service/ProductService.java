package com.supermart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        normalizeAndValidate(product);
        validateUnique(product.getSku(), product.getBarcode(), null);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {

        Product existing = productRepository.findById(id).orElse(null);

        if (existing == null) {
            return null;
        }

        normalizeAndValidate(product);
        validateUnique(product.getSku(), product.getBarcode(), id);

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

    private void normalizeAndValidate(Product product) {

        if (product.getName() == null || product.getName().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Product name is required"
            );
        }

        if (product.getSku() == null || product.getSku().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "SKU is required"
            );
        }

        if (product.getPrice() < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Price cannot be negative"
            );
        }

        if (product.getStockQuantity() < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Stock cannot be negative"
            );
        }

        if (product.getLowStockThreshold() < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Low stock threshold cannot be negative"
            );
        }

        product.setName(product.getName().trim());
        product.setSku(product.getSku().trim());

        if (product.getBarcode() != null) {
            String barcode = product.getBarcode().trim();
            product.setBarcode(barcode.isBlank() ? null : barcode);
        }
    }

    private void validateUnique(
            String sku,
            String barcode,
            Long currentProductId) {

        Optional<Product> skuMatch =
                productRepository.findBySkuIgnoreCase(sku);

        if (skuMatch.isPresent()
                && !skuMatch.get().getId().equals(currentProductId)) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "SKU already exists"
            );
        }

        if (barcode != null && !barcode.isBlank()) {

            Optional<Product> barcodeMatch =
                    productRepository.findByBarcodeIgnoreCase(barcode);

            if (barcodeMatch.isPresent()
                    && !barcodeMatch.get().getId().equals(currentProductId)) {

                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Barcode already exists"
                );
            }
        }
    }
}
package com.supermart.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermart.entity.Product;
import com.supermart.entity.StockHistory;
import com.supermart.repository.ProductRepository;
import com.supermart.repository.StockHistoryRepository;

@Service
public class InventoryService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockHistoryRepository stockHistoryRepository;

    public Product addStock(Long productId, int quantity) {

        Product product = productRepository.findById(productId).orElse(null);

        if (product == null) {
            return null;
        }

        int oldStock = product.getStockQuantity();
        int newStock = oldStock + quantity;

        product.setStockQuantity(newStock);
        productRepository.save(product);

        StockHistory history = new StockHistory();
        history.setProduct(product);
        history.setQuantityChange(quantity);
        history.setPreviousQuantity(oldStock);
        history.setNewQuantity(newStock);
        history.setType(StockHistory.StockChangeType.STOCK_IN);
        history.setReason("Stock added");
        history.setCreatedAt(LocalDateTime.now());

        stockHistoryRepository.save(history);

        return product;
    }

    public Product adjustStock(Long productId, int newQuantity) {

        Product product = productRepository.findById(productId).orElse(null);

        if (product == null) {
            return null;
        }

        int oldStock = product.getStockQuantity();
        int change = newQuantity - oldStock;

        product.setStockQuantity(newQuantity);
        productRepository.save(product);

        StockHistory history = new StockHistory();
        history.setProduct(product);
        history.setQuantityChange(change);
        history.setPreviousQuantity(oldStock);
        history.setNewQuantity(newQuantity);
        history.setType(StockHistory.StockChangeType.ADJUSTMENT);
        history.setReason("Stock adjusted");
        history.setCreatedAt(LocalDateTime.now());

        stockHistoryRepository.save(history);

        return product;
    }

    public List<StockHistory> getStockHistory(Long productId) {
        return stockHistoryRepository.findByProductId(productId);
    }

    public List<Product> getLowStockProducts() {

        return productRepository.findAll()
                .stream()
                .filter(p -> p.getStockQuantity() <= p.getLowStockThreshold())
                .toList();
    }
}
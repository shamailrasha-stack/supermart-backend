package com.supermart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.supermart.entity.Product;
import com.supermart.entity.StockHistory;
import com.supermart.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PutMapping("/{productId}/add")
    public Product addStock(
            @PathVariable Long productId,
            @RequestParam int quantity) {
        return inventoryService.addStock(productId, quantity);
    }

    @PutMapping("/{productId}/adjust")
    public Product adjustStock(
            @PathVariable Long productId,
            @RequestParam int quantity) {
        return inventoryService.adjustStock(productId, quantity);
    }

    @GetMapping("/{productId}/history")
    public List<StockHistory> getHistory(
            @PathVariable Long productId) {
        return inventoryService.getStockHistory(productId);
    }

    @GetMapping("/low-stock")
    public List<Product> getLowStockProducts() {
        return inventoryService.getLowStockProducts();
    }
}
package com.supermart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.supermart.dto.SaleRequest;
import com.supermart.entity.Sale;
import com.supermart.service.SaleService;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin(origins = "http://localhost:5173")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping
    public Sale createSale(@RequestBody SaleRequest request) {
        return saleService.createSale(request);
    }

    @GetMapping
    public List<Sale> getSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/{id}")
    public Sale getSale(@PathVariable Long id) {
        return saleService.getSale(id);
    }

    @GetMapping("/cashier/{cashierId}")
    public List<Sale> getCashierSales(
            @PathVariable Long cashierId) {

        return saleService.getCashierSales(cashierId);
    }
}
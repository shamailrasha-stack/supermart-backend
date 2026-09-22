package com.supermart.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermart.entity.Product;
import com.supermart.entity.Sale;
import com.supermart.repository.ProductRepository;
import com.supermart.repository.SaleRepository;

@Service
public class DashboardService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleRepository saleRepository;

    public Map<String, Object> getDashboard() {

        List<Product> products = productRepository.findAll();
        List<Sale> sales = saleRepository.findAll();

        int totalProducts = products.size();

        int totalStock = products.stream()
                .mapToInt(Product::getStockQuantity)
                .sum();

        long lowStock = products.stream()
                .filter(p -> p.getStockQuantity() <= p.getLowStockThreshold())
                .count();

        LocalDate today = LocalDate.now(ZoneId.of("Asia/Kolkata"));

        double todaysSales = sales.stream()
                .filter(s -> s.getCreatedAt() != null
                        && s.getCreatedAt().toLocalDate().equals(today))
                .mapToDouble(Sale::getTotal)
                .sum();

        long todaysTransactions = sales.stream()
                .filter(s -> s.getCreatedAt() != null
                        && s.getCreatedAt().toLocalDate().equals(today))
                .count();

        Map<String, Double> last7Days = new LinkedHashMap<>();

        for (int i = 6; i >= 0; i--) {

            LocalDate date = today.minusDays(i);

            double amount = sales.stream()
                    .filter(s -> s.getCreatedAt() != null
                            && s.getCreatedAt().toLocalDate().equals(date))
                    .mapToDouble(Sale::getTotal)
                    .sum();

            last7Days.put(date.toString(), amount);
        }

        Map<String, Object> result = new LinkedHashMap<>();

        result.put("totalProducts", totalProducts);
        result.put("totalStock", totalStock);
        result.put("todaysSales", todaysSales);
        result.put("todaysTransactions", todaysTransactions);
        result.put("lowStockProducts", lowStock);
        result.put("last7DaysSales", last7Days);

        return result;
    }
}
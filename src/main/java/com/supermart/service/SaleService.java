package com.supermart.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supermart.dto.SaleRequest;
import com.supermart.entity.Product;
import com.supermart.entity.Sale;
import com.supermart.entity.SaleItem;
import com.supermart.entity.StockHistory;
import com.supermart.entity.User;
import com.supermart.repository.ProductRepository;
import com.supermart.repository.SaleRepository;
import com.supermart.repository.StockHistoryRepository;
import com.supermart.repository.UserRepository;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StockHistoryRepository stockHistoryRepository;

    @Transactional
    public Sale createSale(SaleRequest request) {

        User cashier = userRepository.findById(request.getCashierId()).orElse(null);

        if (cashier == null) {
            throw new RuntimeException("Cashier not found");
        }

        Sale sale = new Sale();

        sale.setInvoiceNumber("INV-" + System.currentTimeMillis());
        sale.setCashier(cashier);
        sale.setDiscount(request.getDiscount());

        sale.setCreatedAt(
                LocalDateTime.now(ZoneId.of("Asia/Kolkata"))
        );

        sale.setStatus(Sale.SaleStatus.COMPLETED);

        Sale.PaymentMethod paymentMethod =
                Sale.PaymentMethod.valueOf(
                        request.getPaymentMethod().toUpperCase()
                );

        sale.setPaymentMethod(paymentMethod);

        double subtotal = 0;

        for (SaleRequest.Item requestItem : request.getItems()) {

            Product product = productRepository
                    .findById(requestItem.getProductId())
                    .orElse(null);

            if (product == null) {
                throw new RuntimeException("Product not found");
            }

            if (!product.isActive()) {
                throw new RuntimeException("Product is inactive");
            }

            if (requestItem.getQuantity() <= 0) {
                throw new RuntimeException("Quantity must be greater than zero");
            }

            if (requestItem.getQuantity() > product.getStockQuantity()) {
                throw new RuntimeException(
                        "Not enough stock for " + product.getName()
                );
            }

            double itemTotal =
                    product.getPrice() * requestItem.getQuantity();

            SaleItem saleItem = new SaleItem();

            saleItem.setSale(sale);
            saleItem.setProduct(product);
            saleItem.setQuantity(requestItem.getQuantity());
            saleItem.setUnitPrice(product.getPrice());
            saleItem.setSubtotal(itemTotal);

            sale.getItems().add(saleItem);

            int oldStock = product.getStockQuantity();

            product.setStockQuantity(
                    oldStock - requestItem.getQuantity()
            );

            productRepository.save(product);

            StockHistory history = new StockHistory();

            history.setProduct(product);
            history.setQuantityChange(-requestItem.getQuantity());
            history.setPreviousQuantity(oldStock);
            history.setNewQuantity(product.getStockQuantity());
            history.setType(StockHistory.StockChangeType.SALE);
            history.setReason("Product sold");

            history.setCreatedAt(
                    LocalDateTime.now(ZoneId.of("Asia/Kolkata"))
            );

            stockHistoryRepository.save(history);

            subtotal += itemTotal;
        }

        double total = subtotal - request.getDiscount();

        if (total < 0) {
            total = 0;
        }

        sale.setSubtotal(subtotal);
        sale.setTotal(total);

        return saleRepository.save(sale);
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public List<Sale> getCashierSales(Long cashierId) {
        return saleRepository.findByCashierId(cashierId);
    }

    public Sale getSale(Long id) {
        return saleRepository.findById(id).orElse(null);
    }
}
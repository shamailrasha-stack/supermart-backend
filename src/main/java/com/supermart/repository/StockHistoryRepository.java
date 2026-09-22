package com.supermart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.supermart.entity.StockHistory;

public interface StockHistoryRepository extends JpaRepository<StockHistory, Long> {

    List<StockHistory> findByProductId(Long productId);
}
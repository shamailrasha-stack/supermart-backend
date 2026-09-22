package com.supermart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.supermart.entity.SaleItem;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
}
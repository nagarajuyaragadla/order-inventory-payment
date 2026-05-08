package com.microservices.inventory.repository;

import com.microservices.inventory.entity.Inventory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, String> {

    Optional<Inventory> findByProductCode(String productCode);
}
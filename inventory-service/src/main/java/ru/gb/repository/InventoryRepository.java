package ru.gb.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.model.Inventory;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
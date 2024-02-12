package ru.gb.service;

import ru.gb.dto.InventoryDto;
import ru.gb.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {
    List<InventoryResponse> isInStock(List<String> skuCode);
    List<InventoryDto> getAll();
}

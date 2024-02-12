package ru.gb.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.dto.InventoryDto;
import ru.gb.dto.InventoryResponse;
import ru.gb.model.Inventory;
import ru.gb.repository.InventoryRepository;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @SneakyThrows
    @Override
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        log.info("Checking Inventory");
        List<InventoryResponse> responses = inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build()
                ).toList();

        return responses;
    }

    @Override
    public List<InventoryDto> getAll() {
        List<Inventory> inventories = inventoryRepository.findAll().stream().sorted(Comparator.comparing(Inventory::getSkuCode)).toList();
        log.info("Get all inventories command");
        return inventories.stream().map(this::mapToInventoryDto).toList();
    }

    private InventoryDto mapToInventoryDto(Inventory inventory) {
        return InventoryDto.builder()
                .id(inventory.getId())
                .skuCode(inventory.getSkuCode())
                .quantity(inventory.getQuantity())
                .build();
    }
}
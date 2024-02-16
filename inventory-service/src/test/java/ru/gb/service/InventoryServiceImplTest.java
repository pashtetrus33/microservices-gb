package ru.gb.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.gb.dto.InventoryDto;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class InventoryServiceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService inventoryService;


    @Test
    public void getAllTest() throws Exception {
        InventoryDto inventory1 = new InventoryDto(1L, "IPhone_13", 100);
        InventoryDto inventory2 = new InventoryDto(1L, "XIAOMI M5", 10);
        InventoryDto inventory3 = new InventoryDto(1L, "Samsung S22", 50);


        when(inventoryService.getAll()).thenReturn(List.of(inventory1, inventory2, inventory3));

        this.mockMvc
                .perform(get("/api/inventory/getall"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

        Mockito.verify(inventoryService, times(1)).getAll();
    }
}
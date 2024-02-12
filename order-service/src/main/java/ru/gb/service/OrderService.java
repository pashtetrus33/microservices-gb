package ru.gb.service;

import ru.gb.dto.OrderRequest;

public interface OrderService {
    String placeOrder(OrderRequest orderRequest);
}

package ru.gb.service;

import ru.gb.dto.ProductRequest;
import ru.gb.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();

    void deleteProductById(String id);

    ProductResponse updateProduct(String id, ProductRequest productRequest);

    ProductResponse getProductById(String id);
}

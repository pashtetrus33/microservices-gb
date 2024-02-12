package ru.gb.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gb.dto.ProductRequest;
import ru.gb.dto.ProductResponse;
import ru.gb.exception.ProductNotFoundException;
import ru.gb.model.Product;
import ru.gb.repository.ProductRepository;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
        return mapToProductResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll().stream().sorted(Comparator.comparing(Product::getName)).toList();
        log.info("Get all products command");
        return products.stream().map(this::mapToProductResponse).toList();
    }

    @Override
    public ProductResponse getProductById(String id) {
        log.info("Get product by id {}", id);
        return productRepository.findById(id).map(this::mapToProductResponse).orElseThrow(() -> new ProductNotFoundException("Product: " + id + " not found!"));
    }

    @Override
    public void deleteProductById(String id) {

        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            log.info("Product {} is deleted", id);
            return;
        }
        throw new ProductNotFoundException("Product with id " + id + " is not found.");
    }

    @Override
    public ProductResponse updateProduct(String id, ProductRequest productRequest) {
        if (productRepository.findById(id).isPresent()) {
            Product updatedProduct = productRepository.findById(id).get();
            updatedProduct.setName(productRequest.getName());
            updatedProduct.setDescription(productRequest.getDescription());
            updatedProduct.setPrice(productRequest.getPrice());
            productRepository.save(updatedProduct);
            log.info("Product {} is updated", updatedProduct.getId());
            return mapToProductResponse(updatedProduct);
        }

        throw new ProductNotFoundException("Product with id " + id + " is not found.");
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
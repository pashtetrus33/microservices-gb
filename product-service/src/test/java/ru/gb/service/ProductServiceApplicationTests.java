package ru.gb.service;

import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.gb.JUnitSpringBootBase;
import ru.gb.model.Product;
import ru.gb.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


class ProductServiceApplicationTests extends JUnitSpringBootBase {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    ProductRepository productRepository;

    @Data
    static class JUnitProductResponse {
        private String id;
        private String name;
        private String description;
        private BigDecimal price;
    }

    @Test
    void testFindByIdSuccess() {
        // подготовил данные
        Product expected = productRepository.save(Product.builder()
                .name("beef")
                .description("food")
                .price(new BigDecimal(650))
                .build());

        JUnitProductResponse responseBody = webTestClient.get()
                .uri("/api/product/" + expected.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(JUnitProductResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(expected.getId(), responseBody.getId());
        Assertions.assertEquals(expected.getName(), responseBody.getName());
    }

    @Test
    void testFindByIdNotFound() {

        webTestClient.get()
                .uri("/api/product/" + UUID.randomUUID())
                .exchange()
                .expectStatus().isNotFound();
    }


    @Test
    void testGetAll() {
        // подготовил данные
        productRepository.saveAll(List.of(
                Product.builder()
                        .name("beef")
                        .description("food")
                        .price(new BigDecimal(650))
                        .build(),
                Product.builder()
                        .name("water")
                        .description("drinks")
                        .price(new BigDecimal(150))
                        .build(),
                Product.builder()
                        .name("chair")
                        .description("furniture")
                        .price(new BigDecimal(250))
                        .build()
        ));

        List<Product> expected = productRepository.findAll();

        List<JUnitProductResponse> responseBody = webTestClient.get()
                .uri("/api/product")
                // .retrieve
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<JUnitProductResponse>>() {
                })
                .returnResult()
                .getResponseBody();

        Assertions.assertEquals(expected.size(), responseBody.size());
        for (JUnitProductResponse customerResponse : responseBody) {
            boolean found = expected.stream()
                    .filter(it -> Objects.equals(it.getId(), customerResponse.getId()))
                    .anyMatch(it -> Objects.equals(it.getName(), customerResponse.getName()));
            Assertions.assertTrue(found);
        }
    }
}
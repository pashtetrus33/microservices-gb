package ru.gb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.gb.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}

package com.elasticsearchexample.service;

import com.elasticsearchexample.model.Product;
import com.elasticsearchexample.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return StreamSupport
                .stream(productRepository.findAll().spliterator(), false)
                .toList();
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductsByTitleAndDescription(String title) {
        return productRepository.findByTitleAndDescriptionContaining(title);
    }

    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public Iterable<Product> saveAllProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }

}

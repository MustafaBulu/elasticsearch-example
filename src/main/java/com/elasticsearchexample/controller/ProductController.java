package com.elasticsearchexample.controller;

import com.elasticsearchexample.mapper.ProductMapper;
import com.elasticsearchexample.model.Product;
import com.elasticsearchexample.model.dto.ProductDto;
import com.elasticsearchexample.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(productMapper.toDto(savedProduct));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<ProductDto>> createProducts(@RequestBody List<ProductDto> productDtoList) {
        List<Product> products = productDtoList.stream()
                .map(productMapper::toEntity)
                .toList();
        Iterable<Product> savedProducts = productService.saveAllProducts(products);
        List<ProductDto> savedProductDtos = StreamSupport
                .stream(savedProducts.spliterator(), false)
                .map(productMapper::toDto)
                .toList();

        return ResponseEntity.ok(savedProductDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String id) {
        return productService.getProductById(id)
                .map(productMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDto> productDtoList = products.stream()
                .map(productMapper::toDto)
                .toList();
        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<ProductDto>> searchByTitle(@RequestParam String keyword) {
        List<Product> products = productService.getProductsByTitleAndDescription(keyword);
        List<ProductDto> productDtoList = products.stream()
                .map(productMapper::toDto)
                .toList();
        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping("/search/price")
    public ResponseEntity<List<ProductDto>> searchByPriceRange(
            @RequestParam double minPrice,
            @RequestParam double maxPrice
    ) {
        List<Product> products = productService.getProductsByPriceRange(minPrice, maxPrice);
        List<ProductDto> productDtoList = products.stream()
                .map(productMapper::toDto)
                .toList();
        return ResponseEntity.ok(productDtoList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
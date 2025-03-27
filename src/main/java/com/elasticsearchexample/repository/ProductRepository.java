package com.elasticsearchexample.repository;

import com.elasticsearchexample.model.Product;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductRepository extends ElasticsearchRepository<Product, String> {
    @Query("{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"title\", \"description\"], \"fuzziness\": \"AUTO\"}}")
    List<Product> findByTitleAndDescriptionContaining(String keyword);

    List<Product> findByPriceBetween(double minPrice, double maxPrice);

}

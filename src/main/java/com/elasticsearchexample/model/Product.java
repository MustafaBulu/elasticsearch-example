package com.elasticsearchexample.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "products")
@Data
public class Product {
    @Id
    private String id;
    private String title;
    private String description;
    private double price;
}

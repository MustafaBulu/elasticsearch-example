package com.elasticsearchexample.mapper;

import com.elasticsearchexample.model.Product;
import com.elasticsearchexample.model.dto.ProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(Product product);
    Product toEntity(ProductDto productDto);
}

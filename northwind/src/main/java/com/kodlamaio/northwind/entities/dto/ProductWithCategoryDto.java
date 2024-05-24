package com.kodlamaio.northwind.entities.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductWithCategoryDto {
    private int id;
    private String productName;
    private String categoryName;
    public ProductWithCategoryDto(int id, String productName, String categoryName) {
        this.id = id;
        this.productName = productName;
        this.categoryName = categoryName;
    }
    
}
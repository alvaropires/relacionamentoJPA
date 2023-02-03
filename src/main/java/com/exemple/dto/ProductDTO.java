package com.exemple.dto;

import com.exemple.model.Category;
import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private Category category;
    private Double price;
    private Boolean isActive;

    public String getCategory(){
        return category.getName();
    }
}

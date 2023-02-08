package com.exemple.dto.response;

import com.exemple.model.Category;
import lombok.Data;

@Data
public class ProductResponse {
    private String name;
    private Category category;
    private Double price;
    private Boolean isActive;

    public String getCategory(){
        return category.getName();
    }
}

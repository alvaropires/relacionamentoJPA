package com.exemple.dto;

import com.exemple.model.Category;
import com.exemple.model.Product;
import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private Category category;
    private Double price;
    private Boolean isActive;

    public ProductDTO(Product product){
        name = product.getName();
        category = product.getCategory();
        price = product.getPrice();
        isActive = product.getIsActive();
    }

    public String getCategory(){
        return category.getName();
    }
}

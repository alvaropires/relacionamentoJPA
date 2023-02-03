package com.exemple.dto;

import com.exemple.model.Category;
import lombok.Data;

@Data
public class ProductCreateDTO {
    private String name;
    private Long category;
    private Double price;
    private Boolean isActive = true;
}

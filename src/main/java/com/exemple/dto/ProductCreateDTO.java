package com.exemple.dto;

import lombok.Data;

@Data
public class ProductCreateDTO {
    private String name;
    private Long category;
    private Double price;
    private Boolean isActive = true;
}

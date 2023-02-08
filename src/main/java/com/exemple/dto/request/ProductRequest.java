package com.exemple.dto.request;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private Long category;
    private Double price;
    private Boolean isActive = true;
}

package com.exemple.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {
    private String name;
    private List<ProductResponse> products;

}
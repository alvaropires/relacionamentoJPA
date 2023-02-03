package com.exemple.dto;

import com.exemple.model.Category;
import com.exemple.model.Product;
import com.exemple.service.CategoryService;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CategoryDTO {
    private String name;
    private List<ProductDTO> products;

    public CategoryDTO(Category category){
        name = category.getName();
        products = category.getProducts().stream().map(ProductDTO::new).collect(Collectors.toList());
    }
}
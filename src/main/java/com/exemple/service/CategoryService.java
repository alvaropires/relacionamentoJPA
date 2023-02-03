package com.exemple.service;

import com.exemple.dto.CategoryCreateDTO;
import com.exemple.dto.CategoryDTO;
import com.exemple.model.Category;
import com.exemple.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    public CategoryRepository categoryRepository;

    public CategoryDTO create(Category category){
        categoryRepository.save(category);
        return new CategoryDTO(category);
    }
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
    public Optional<Category> findById(Long id){
        return categoryRepository.findById(id);
    }
    public CategoryDTO toCategoryDTO(Category category){
        return new CategoryDTO(category);
    }

    public List<CategoryDTO> toCategoryDTOList(List<Category> categories){
        return categories.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }
    public Category toCategory(CategoryCreateDTO dto){
        Category category = new Category();
        category.setName(dto.getName());
        category.setProducts(new ArrayList<>());
        return category;
    }
}

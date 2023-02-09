package com.exemple.service;

import com.exemple.dto.request.CategoryRequest;
import com.exemple.dto.response.CategoryResponse;
import com.exemple.model.Category;
import com.exemple.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper){
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public CategoryResponse create(Category category){
        categoryRepository.save(category);
        return this.toCategoryResponse(category);
    }
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
    public Category findById(Long id){
        return categoryRepository.findById(id).orElseThrow();
    }

    public void deleteById(Long id) {
        findById(id);
        categoryRepository.deleteById(id);
    }

    public Category update(Long id, CategoryRequest categoryRequest){
        Category category = findById(id);
        category.setName(categoryRequest.getName());
        categoryRepository.save(category);
        return category;
    }
    public CategoryResponse toCategoryResponse(Category category){
        return modelMapper.map(category, CategoryResponse.class);
    }

    public List<CategoryResponse> toCategoryResponseList(List<Category> categories){
        return categories.stream().map(this::toCategoryResponse).collect(Collectors.toList());
    }
    public Category toCategory(CategoryRequest categoryRequest){
        Category category = modelMapper.map(categoryRequest, Category.class);
        category.setProducts(new ArrayList<>());
        return category;
    }

}

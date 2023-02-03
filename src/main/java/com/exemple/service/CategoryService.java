package com.exemple.service;

import com.exemple.dto.CategoryCreateDTO;
import com.exemple.dto.CategoryDTO;
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

    public CategoryDTO create(Category category){
        categoryRepository.save(category);
        return this.toCategoryDTO(category);
    }
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
    public Optional<Category> findById(Long id){
        return categoryRepository.findById(id);
    }
    public CategoryDTO toCategoryDTO(Category category){
        return modelMapper.map(category, CategoryDTO.class);
    }

    public List<CategoryDTO> toCategoryDTOList(List<Category> categories){
        return categories.stream().map(this::toCategoryDTO).collect(Collectors.toList());
    }
    public Category toCategory(CategoryCreateDTO dto){
        Category category = modelMapper.map(dto, Category.class);
        category.setProducts(new ArrayList<>());
        return category;
    }
}

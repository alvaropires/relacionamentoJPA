package com.exemple.controller;

import com.exemple.dto.CategoryCreateDTO;
import com.exemple.dto.CategoryDTO;
import com.exemple.model.Category;
import com.exemple.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@Tag(name = "Categories")
public class CategoryController {
    @Autowired
    public CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){
        var categories = categoryService.findAll();
        List<CategoryDTO> result = categoryService.toCategoryDTOList(categories);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<CategoryDTO>> findById(@PathVariable Long id){
        var category = categoryService.findById(id);
        CategoryDTO categoryDTO = categoryService.toCategoryDTO(category.orElseThrow());
        return new ResponseEntity<>(Optional.of(categoryDTO), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryCreateDTO dto){
        Category category = categoryService.toCategory(dto);
        var create = categoryService.create(category);
        return new ResponseEntity<>(create, HttpStatus.CREATED);
    }
}

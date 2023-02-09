package com.exemple.controller;

import com.exemple.dto.request.CategoryRequest;
import com.exemple.dto.response.CategoryResponse;
import com.exemple.model.Category;
import com.exemple.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(description = "Find All Categories.")
    public ResponseEntity<List<CategoryResponse>> findAll(){
        var categories = categoryService.findAll();
        List<CategoryResponse> result = categoryService.toCategoryResponseList(categories);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @Operation(description = "Find Categories By Id.")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id){
        var category = categoryService.findById(id);
        CategoryResponse categoryResponse = categoryService.toCategoryResponse(category);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @PostMapping
    @Operation(description = "Create A New Category.")
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest categoryRequest){
        Category category = categoryService.toCategory(categoryRequest);
        var create = categoryService.create(category);
        return new ResponseEntity<>(create, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete A Category By ID.")
    public ResponseEntity deleteById(@PathVariable Long id){
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(description = "Update A Category By ID")
    public ResponseEntity<CategoryResponse> updateById(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest){
        Category category = categoryService.update(id, categoryRequest);
        CategoryResponse categoryResponse = categoryService.toCategoryResponse(category);
        return new ResponseEntity<>(categoryResponse, HttpStatus.CREATED);
    }
}

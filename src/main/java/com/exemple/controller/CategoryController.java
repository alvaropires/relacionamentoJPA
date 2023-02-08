package com.exemple.controller;

import com.exemple.dto.request.CategoryRequest;
import com.exemple.dto.response.CategoryResponse;
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
    public ResponseEntity<List<CategoryResponse>> findAll(){
        var categories = categoryService.findAll();
        List<CategoryResponse> result = categoryService.toCategoryResponseList(categories);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<CategoryResponse>> findById(@PathVariable Long id){
        var category = categoryService.findById(id);
        CategoryResponse categoryResponse = categoryService.toCategoryResponse(category.orElseThrow());
        return new ResponseEntity<>(Optional.of(categoryResponse), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest categoryRequest){
        Category category = categoryService.toCategory(categoryRequest);
        var create = categoryService.create(category);
        return new ResponseEntity<>(create, HttpStatus.CREATED);
    }
}

package com.exemple.controller;

import com.exemple.dto.request.ProductRequest;
import com.exemple.dto.response.ProductResponse;
import com.exemple.model.Product;
import com.exemple.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@Tag(name = "Products")
public class ProductController {
    @Autowired
    public ProductService productService;

    @GetMapping
    @Operation(description = "Find All Products.")
    public ResponseEntity<List<ProductResponse>> findAll(){
        var products = productService.findAll();
        List<ProductResponse> productResponseList = productService.toProductResponseList(products);
        return new ResponseEntity<>(productResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(description = "Find Products By Id.")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id){
        var product = productService.findById(id);
        ProductResponse productResponse = productService.toProductResponse(product);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
    @PostMapping()
    @Operation(description = "Create A New Product.")
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest productRequest){
        var product = productService.toProduct(productRequest);
        productService.create(product);
        var result = productService.toProductResponse(product);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete A Product By ID.")
    public ResponseEntity delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(description = "Update A Product By ID")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @RequestBody ProductRequest productRequest){
        Product product = productService.update(id, productRequest);
        ProductResponse productResponse = productService.toProductResponse(product);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

}

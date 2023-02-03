package com.exemple.controller;

import com.exemple.dto.ProductCreateDTO;
import com.exemple.dto.ProductDTO;
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
    public ResponseEntity<List<ProductDTO>> findAll(){
        var products = productService.findAll();
        List<ProductDTO> productListDTO = productService.toProductDTOList(products);
        return new ResponseEntity<>(productListDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(description = "Find Products By Id.")
    public ResponseEntity<Optional<ProductDTO>> findById(@PathVariable Long id){
        var product = productService.findById(id);
        ProductDTO productDTO = productService.toProductDTO(product.orElseThrow());
        return new ResponseEntity<>(Optional.ofNullable(productDTO), HttpStatus.OK);
    }
    @PostMapping()
    @Operation(description = "Create A New Product.")
    public ResponseEntity<ProductDTO> create(@RequestBody ProductCreateDTO dto){
        var product = productService.toProduct(dto);
        productService.create(product);
        var result = productService.toProductDTO(product);
        return new ResponseEntity(result, HttpStatus.OK);
    }

}

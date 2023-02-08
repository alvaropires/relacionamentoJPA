package com.exemple.service;

import com.exemple.dto.request.ProductRequest;
import com.exemple.dto.response.ProductResponse;
import com.exemple.model.Category;
import com.exemple.model.Product;
import com.exemple.repository.CategoryRepository;
import com.exemple.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ModelMapper modelMapper){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }
    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }
    public List<Product> findAll(){
        return productRepository.findAll();
    }
    public ProductResponse create(Product product){
        productRepository.save(product);
        return this.toProductResponse(product);
    }

    public ProductResponse toProductResponse(Product product){
        return modelMapper.map(product, ProductResponse.class);
    }

    public List<ProductResponse> toProductResponseList(List<Product> products){
        return products.stream().map(this::toProductResponse).collect(Collectors.toList());
    }
    public Product toProduct(ProductRequest productRequest){
        Category category = categoryRepository.findById(productRequest.getCategory()).orElseThrow();
        Product product = modelMapper.map(productRequest, Product.class);
        product.setCategory(category);
        return product;
    }
}

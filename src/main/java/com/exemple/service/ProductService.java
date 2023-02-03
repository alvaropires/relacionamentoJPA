package com.exemple.service;

import com.exemple.dto.ProductCreateDTO;
import com.exemple.dto.ProductDTO;
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
    public ProductDTO create(Product product){
        System.out.println(product.getCategory().getId());
        Category category = categoryRepository.findById(product.getCategory().getId()).orElseThrow(()-> new IllegalArgumentException("category not found"));
        System.out.println(category);
        product.setCategory(category);
        productRepository.save(product);
        return this.toProductDTO(product);
    }

    public ProductDTO toProductDTO(Product product){
        return modelMapper.map(product, ProductDTO.class);
    }

    public List<ProductDTO> toProductDTOList(List<Product> products){
        return products.stream().map(this::toProductDTO).collect(Collectors.toList());
    }
    public Product toProduct(ProductCreateDTO dto){
        Category category = categoryRepository.findById(dto.getCategory()).orElseThrow();
        Product product = modelMapper.map(dto, Product.class);
        product.setCategory(category);
        return product;
    }
}

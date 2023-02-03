package com.exemple.service;

import com.exemple.dto.ProductCreateDTO;
import com.exemple.dto.ProductDTO;
import com.exemple.model.Category;
import com.exemple.model.Product;
import com.exemple.repository.CategoryRepository;
import com.exemple.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public CategoryRepository categoryRepository;

//    public Optional<ProductDTO> findById(Long id){
//        Optional<Product> product = productRepository.findById(id);
//        ProductDTO result = new ProductDTO(product.orElseThrow());
//        return Optional.of(result);
//    }
    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }
    public List<Product> findAll(){
        return productRepository.findAll();
    }
    public ProductDTO create(Product product){
        Category category = categoryRepository.findById(product.getCategory().getId()).orElseThrow(()-> new IllegalArgumentException("category not found"));
        product.setCategory(category);
        productRepository.save(product);
        return new ProductDTO(product);
    }

    public ProductDTO toProductDTO(Product product){
        return new ProductDTO(product);
    }

    public List<ProductDTO> toProductDTOList(List<Product> products){
        return products.stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    public Product toProduct(ProductCreateDTO dto){
        Category category = categoryRepository.findById(dto.getCategory()).orElseThrow(()->new IllegalArgumentException("category not found"));
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setCategory(category);
        product.setIsActive(dto.getIsActive());
        return product;
    }

}

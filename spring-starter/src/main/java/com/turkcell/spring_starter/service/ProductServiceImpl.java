package com.turkcell.spring_starter.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.turkcell.spring_starter.dto.CreateProductRequest;
import com.turkcell.spring_starter.entity.Category;
import com.turkcell.spring_starter.entity.Product;
import com.turkcell.spring_starter.repository.CategoryRepository;
import com.turkcell.spring_starter.repository.ProductRepository;

@Service
public class ProductServiceImpl {
    private final ProductRepository productRepository;
    private final CategoryServiceImpl categoryServiceImpl;

    public ProductServiceImpl(ProductRepository productRepository, CategoryServiceImpl categoryServiceImpl) {
        this.productRepository = productRepository;
        this.categoryServiceImpl = categoryServiceImpl;
    }

    public void create(@RequestBody CreateProductRequest createProductRequest) {

        Category category = categoryServiceImpl.getById(createProductRequest.categoryId());
        
        if (category == null) 
            throw new RuntimeException("Category not found");

        Product product = new Product();
        product.setName(createProductRequest.name());
        product.setDescription(createProductRequest.description());
        product.setCategory(category);

        productRepository.save(product);
        
    }
 

}

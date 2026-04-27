package com.turkcell.spring_starter.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.turkcell.spring_starter.dto.CreateProductRequest;
import com.turkcell.spring_starter.dto.CreatedProductResponse;
import com.turkcell.spring_starter.dto.ListProductRespone;
import com.turkcell.spring_starter.dto.UpdateProductRequest;
import com.turkcell.spring_starter.entity.Category;
import com.turkcell.spring_starter.entity.Product;
import com.turkcell.spring_starter.repository.CategoryRepository;
import com.turkcell.spring_starter.repository.ProductRepository;

@Service
public class ProductServiceImpl {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public CreatedProductResponse create(CreateProductRequest createProductRequest) {
        Category category = categoryRepository.findById(UUID.fromString(createProductRequest.getCategoryId()))
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setName(createProductRequest.getName());
        product.setDescription(createProductRequest.getDescription());
        product.setCategory(category);

        product = productRepository.save(product);

        CreatedProductResponse response = new CreatedProductResponse();
        response.setId(product.getId().toString());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setCategoryId(product.getCategory().getId().toString());

        return response;
    }

    public ListProductRespone getAll() {
        List<Product> products = productRepository.findAll();
        ListProductRespone response = new ListProductRespone();
        response.setProducts(products);
        return response;
    }

    public CreatedProductResponse getById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CreatedProductResponse response = new CreatedProductResponse();
        response.setId(product.getId().toString());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setCategoryId(product.getCategory().getId().toString());

        return response;
    }

    public CreatedProductResponse update(UUID id, UpdateProductRequest updateProductRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = categoryRepository.findById(UUID.fromString(updateProductRequest.getCategoryId()))
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setName(updateProductRequest.getName());
        product.setDescription(updateProductRequest.getDescription());
        product.setCategory(category);

        product = productRepository.save(product);

        CreatedProductResponse response = new CreatedProductResponse();
        response.setId(product.getId().toString());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setCategoryId(product.getCategory().getId().toString());

        return response;
    }

    public void delete(UUID id) {
        productRepository.deleteById(id);
    }

}

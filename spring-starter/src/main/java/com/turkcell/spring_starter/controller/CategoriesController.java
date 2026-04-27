package com.turkcell.spring_starter.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.spring_starter.dto.CreateCategoryRequest;
import com.turkcell.spring_starter.dto.CreatedCategoryResponse;
import com.turkcell.spring_starter.dto.ListCategoryRespone;
import com.turkcell.spring_starter.dto.UpdateCategoryRequest;
import com.turkcell.spring_starter.service.CategoryServiceImpl;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
    private final CategoryServiceImpl categoryServiceImple;

    public CategoriesController(CategoryServiceImpl categoryServiceImple) {
        this.categoryServiceImple = categoryServiceImple;
    }

    @PostMapping()
    public CreatedCategoryResponse create(@RequestBody CreateCategoryRequest categoryRequest) {
        return categoryServiceImple.create(categoryRequest);
    }

    @GetMapping()
    public ListCategoryRespone getAll() {
        return categoryServiceImple.getAll();
    }

    @GetMapping("/{id}")
    public CreatedCategoryResponse getById(@PathVariable UUID id) {
        return categoryServiceImple.getById(id);
    }

    @PutMapping("/{id}")
    public CreatedCategoryResponse update(@PathVariable UUID id, @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        return categoryServiceImple.update(id, updateCategoryRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        categoryServiceImple.delete(id);
    }

}

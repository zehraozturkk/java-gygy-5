package com.turkcell.spring_starter.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.turkcell.spring_starter.dto.CreateCategoryRequest;
import com.turkcell.spring_starter.dto.CreatedCategoryResponse;
import com.turkcell.spring_starter.dto.ListCategoryRespone;
import com.turkcell.spring_starter.dto.UpdateCategoryRequest;
import com.turkcell.spring_starter.entity.Category;
import com.turkcell.spring_starter.repository.CategoryRepository;

import jakarta.persistence.EntityManager;

@Service
public class CategoryServiceImpl {
    //bu ikisi bizim bağımlılıklarımızdır. controctura eklemlyiiz
    private final CategoryRepository categoryRepository;
    private final EntityManager entityManager;

    public CategoryServiceImpl(CategoryRepository categoryRepository, EntityManager entityManager) {
        this.categoryRepository = categoryRepository;
        this.entityManager = entityManager;
    }

    public CreatedCategoryResponse create(CreateCategoryRequest createCategoryRequest) {
        Category category = new Category();
        category.setName(createCategoryRequest.getName());

        category = this.categoryRepository.save(category);

        CreatedCategoryResponse response = new CreatedCategoryResponse();
        response.setId(category.getId().toString());
        response.setName(category.getName());

        return response;
    }

    public ListCategoryRespone getAll() {
        List<Category> categories = categoryRepository.findAll();
        ListCategoryRespone response = new ListCategoryRespone();
        response.setCategories(categories);
        return response;
    }

    public CreatedCategoryResponse getById(UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        CreatedCategoryResponse response = new CreatedCategoryResponse();
        response.setId(category.getId().toString());
        response.setName(category.getName());

        return response;
    }

    public CreatedCategoryResponse update(UUID id, UpdateCategoryRequest updateCategoryRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setName(updateCategoryRequest.getName());
        category = categoryRepository.save(category);

        CreatedCategoryResponse response = new CreatedCategoryResponse();
        response.setId(category.getId().toString());
        response.setName(category.getName());

        return response;
    }

    public void delete(UUID id) {
        categoryRepository.deleteById(id);
    }

    public List<ListCategoryRespone> search(String query) {

        //Set<Category> categories = categoryRepository.findByNameLike("%" + query + "%");

        String jpql = "select c from Category c where c.name like :query";

        List<Category> categories = entityManager
                .createQuery(jpql, Category.class)
                .setParameter("query", "%" + query + "%")
                .getResultList();
                
        List<ListCategoryRespone> responseList = new ArrayList<>();

        for (Category category : categories) {
            ListCategoryRespone respone = new ListCategoryRespone();
            respone.setId(category.getId().toString());
            respone.setName(category.getName());
            responseList.add(respone);
        }
        return responseList;
    }

}

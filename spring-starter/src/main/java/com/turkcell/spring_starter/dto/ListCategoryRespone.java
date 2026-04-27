package com.turkcell.spring_starter.dto;

import java.util.List;

import com.turkcell.spring_starter.entity.Category;

public class ListCategoryRespone {

    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}

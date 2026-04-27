package com.turkcell.spring_starter.dto;

//kullanıcın sadece create yaparken atacağı istek
public class CreateCategoryRequest {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

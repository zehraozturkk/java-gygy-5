package com.turkcell.spring_starter.dto;

public class CreatedTagResponse {
    private String id;
    private String name;

    public CreatedTagResponse() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

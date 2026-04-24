package com.turkcell.spring_starter.dto;

import java.util.UUID;

public class ProductCreateRespone {
    private UUID id;
    private String name;
    private double price;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}

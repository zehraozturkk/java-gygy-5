package com.turkcell.spring_starter.dto;

import java.util.List;

import com.turkcell.spring_starter.entity.Product;

public class ListProductRespone {

    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}

package com.turkcell.spring_starter.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.spring_starter.dto.CreateProductRequest;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    @PostMapping
    public void create(@RequestBody CreateProductRequest request) {
        System.out.println();
    }

}

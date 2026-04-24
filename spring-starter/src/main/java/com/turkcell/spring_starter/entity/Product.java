package com.turkcell.spring_starter.entity;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
    //@generatedValue(strategy= GenerationType.Identity) -> bire bir artan strateji
    @Id
    @UuidGenerator()
    @Column(name="customer_id")
    private UUID id;

    @Column(name="name", nullable = false, length = 100)
    private String name;

    @Column(name="description", length = 500)
    private String description;


    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    private Category category;




    public UUID getId() {
        return id;
    }   

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }       

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}

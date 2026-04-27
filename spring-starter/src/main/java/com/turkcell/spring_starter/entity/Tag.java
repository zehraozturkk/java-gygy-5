
package com.turkcell.spring_starter.entity;

import java.util.Set;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @UuidGenerator()
    @Column(name="tag_id")
    private String id;

    @Column(name="name", nullable = false, length = 100)
    private String name;

    @ManyToMany(mappedBy = "tags") //değişken ismidir. tag'e bağlı ürünler
    private Set<Product> products;

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

    // Set yapısı liseteye göre veri tabanındaki array mantığına daha yakındır. Tekrarlamaları engeller. Bir ürün bir tag'e birden fazla kez sahip olamaz.
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}

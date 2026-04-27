package com.turkcell.spring_starter.entity;

import java.util.List;
import java.util.UUID;
import java.util.Set;


import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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


    @ManyToMany
    @JoinTable( //ara tablo oluşturur. many to many ilişkiler için gerkeli
        name = "product_tags",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

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

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

}

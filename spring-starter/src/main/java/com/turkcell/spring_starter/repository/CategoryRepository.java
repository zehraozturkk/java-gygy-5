package com.turkcell.spring_starter.repository;

import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.turkcell.spring_starter.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID>{

    //JPQL sorgusudur. SQL'e benzer ama entity'ler üzerinden sorgu yapar.
    @Query("SELECT c FROM Category c WHERE c.name = :query")
    Set<Category> search(String query);


    // named querydir 
    Category findByName(String name);

    
    

}

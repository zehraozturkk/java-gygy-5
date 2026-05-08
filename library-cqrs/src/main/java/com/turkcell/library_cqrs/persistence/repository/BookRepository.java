package com.turkcell.library_cqrs.persistence.repository;

import com.turkcell.library_cqrs.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    boolean existsByName(String name);
}

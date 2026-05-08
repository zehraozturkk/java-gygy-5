package com.turkcell.library_cqrs.infrastructure.repository;

import com.turkcell.library_cqrs.infrastructure.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {}

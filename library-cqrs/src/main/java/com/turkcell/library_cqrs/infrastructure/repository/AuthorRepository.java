package com.turkcell.library_cqrs.infrastructure.repository;

import com.turkcell.library_cqrs.infrastructure.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {}

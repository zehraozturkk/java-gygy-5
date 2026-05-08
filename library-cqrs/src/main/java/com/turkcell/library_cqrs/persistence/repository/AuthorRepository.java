package com.turkcell.library_cqrs.persistence.repository;

import com.turkcell.library_cqrs.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
}

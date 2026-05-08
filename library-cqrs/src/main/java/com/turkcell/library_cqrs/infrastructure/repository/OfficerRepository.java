package com.turkcell.library_cqrs.infrastructure.repository;

import com.turkcell.library_cqrs.infrastructure.entity.Officer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfficerRepository extends JpaRepository<Officer, Integer> {
    Optional<Officer> findByUsername(String username);
    boolean existsByUsername(String username);
}

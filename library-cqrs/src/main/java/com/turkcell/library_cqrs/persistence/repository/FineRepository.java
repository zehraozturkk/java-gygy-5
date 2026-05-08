package com.turkcell.library_cqrs.persistence.repository;

import com.turkcell.library_cqrs.domain.Fine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FineRepository extends JpaRepository<Fine, UUID> {
}

package com.turkcell.library_cqrs.persistence.repository;

import com.turkcell.library_cqrs.domain.Barrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BarrowRepository extends JpaRepository<Barrow, UUID> {
}

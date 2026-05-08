package com.turkcell.library_cqrs.infrastructure.repository;

import com.turkcell.library_cqrs.infrastructure.entity.Barrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BarrowRepository extends JpaRepository<Barrow, Integer> {
    List<Barrow> findByStatus(String status);
}

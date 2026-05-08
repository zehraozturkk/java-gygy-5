package com.turkcell.library_system.repository;

import com.turkcell.library_system.entity.Fine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FineRepository extends JpaRepository<Fine, Integer> {
    List<Fine> findByIsPaid(boolean isPaid);
    List<Fine> findByBarrowId(Integer barrowId);
}

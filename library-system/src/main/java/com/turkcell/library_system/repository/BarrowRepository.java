package com.turkcell.library_system.repository;

import com.turkcell.library_system.entity.Barrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BarrowRepository extends JpaRepository<Barrow, Integer> {
    List<Barrow> findByStudentId(Integer studentId);
    List<Barrow> findByStatus(String status);
}

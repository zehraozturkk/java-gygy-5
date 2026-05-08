package com.turkcell.library_cqrs.infrastructure.repository;

import com.turkcell.library_cqrs.infrastructure.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {}

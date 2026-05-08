package com.turkcell.library_cqrs.persistence.repository;

import com.turkcell.library_cqrs.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    Optional<Student> findByPhone(String phone);
}

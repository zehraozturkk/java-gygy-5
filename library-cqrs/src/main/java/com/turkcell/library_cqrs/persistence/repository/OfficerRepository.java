package com.turkcell.library_cqrs.persistence.repository;

import com.turkcell.library_cqrs.domain.Officer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OfficerRepository extends JpaRepository<Officer, UUID> {
}

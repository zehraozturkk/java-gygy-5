package com.turkcell.library_cqrs.application.features.student.rule;

import com.turkcell.library_cqrs.persistence.repository.StudentRepository;
import org.springframework.stereotype.Component;

@Component
public class StudentBusinessRules {

    private final StudentRepository studentRepository;

    public StudentBusinessRules(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void studentWithSamePhoneMustNotExist(String phone) {
        studentRepository.findByPhone(phone).ifPresent(s -> {
            throw new RuntimeException("Bu telefon numarasıyla kayıtlı öğrenci zaten mevcut.");
        });
    }
}

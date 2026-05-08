package com.turkcell.library_system.service;

import com.turkcell.library_system.dto.*;
import com.turkcell.library_system.entity.Student;
import com.turkcell.library_system.exception.EntityNotFoundException;
import com.turkcell.library_system.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentResponse> getAll() {
        return studentRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public StudentResponse getById(Integer id) {
        Student student = findOrThrow(id);
        return toResponse(student);
    }

    public StudentResponse create(CreateStudentRequest request) {
        Student student = new Student();
        student.setName(request.name());
        student.setSurname(request.surname());
        student.setPhone(request.phone());
        return toResponse(studentRepository.save(student));
    }

    public StudentResponse update(Integer id, UpdateStudentRequest request) {
        Student student = findOrThrow(id);
        student.setName(request.name());
        student.setSurname(request.surname());
        student.setPhone(request.phone());
        return toResponse(studentRepository.save(student));
    }

    public void delete(Integer id) {
        findOrThrow(id);
        studentRepository.deleteById(id);
    }

    // Diğer servislerden entity olarak çekmek için
    public Student getEntityById(Integer id) {
        return findOrThrow(id);
    }

    private Student findOrThrow(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student", id));
    }

    private StudentResponse toResponse(Student s) {
        return new StudentResponse(s.getId(), s.getName(), s.getSurname(), s.getPhone());
    }
}

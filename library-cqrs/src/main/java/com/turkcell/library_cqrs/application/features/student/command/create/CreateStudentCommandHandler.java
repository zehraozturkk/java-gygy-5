package com.turkcell.library_cqrs.application.features.student.command.create;

import com.turkcell.library_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs.infrastructure.entity.Student;
import com.turkcell.library_cqrs.infrastructure.repository.StudentRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateStudentCommandHandler implements CommandHandler<CreateStudentCommand, Integer> {

    private final StudentRepository studentRepository;

    public CreateStudentCommandHandler(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Integer handle(CreateStudentCommand command) {
        Student student = new Student();
        student.setName(command.name());
        student.setSurname(command.surname());
        student.setPhone(command.phone());
        return studentRepository.save(student).getId();
    }
}

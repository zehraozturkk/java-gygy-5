package com.turkcell.library_cqrs.application.features.student.mapper;

import com.turkcell.library_cqrs.application.features.student.command.create.CreateStudentCommand;
import com.turkcell.library_cqrs.application.features.student.command.create.CreatedStudentResponse;
import com.turkcell.library_cqrs.domain.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student studentFromCreateCommand(CreateStudentCommand command) {
        Student student = new Student();
        student.setName(command.name());
        student.setSurname(command.surname());
        student.setPhone(command.phone());
        return student;
    }

    public CreatedStudentResponse createdStudentResponseFromStudent(Student student) {
        return new CreatedStudentResponse(student.getId(), student.getName(), student.getSurname(), student.getPhone());
    }
}

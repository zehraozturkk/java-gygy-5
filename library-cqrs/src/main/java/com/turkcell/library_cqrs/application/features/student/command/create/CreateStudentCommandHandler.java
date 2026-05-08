package com.turkcell.library_cqrs.application.features.student.command.create;

import com.turkcell.library_cqrs.application.features.student.mapper.StudentMapper;
import com.turkcell.library_cqrs.application.features.student.rule.StudentBusinessRules;
import com.turkcell.library_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs.domain.Student;
import com.turkcell.library_cqrs.persistence.repository.StudentRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateStudentCommandHandler implements CommandHandler<CreateStudentCommand, CreatedStudentResponse> {

    private final StudentRepository studentRepository;
    private final StudentBusinessRules studentBusinessRules;
    private final StudentMapper studentMapper;

    public CreateStudentCommandHandler(StudentRepository studentRepository,
                                       StudentBusinessRules studentBusinessRules,
                                       StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentBusinessRules = studentBusinessRules;
        this.studentMapper = studentMapper;
    }

    @Override
    public CreatedStudentResponse handle(CreateStudentCommand command) {
        studentBusinessRules.studentWithSamePhoneMustNotExist(command.phone());

        Student student = studentMapper.studentFromCreateCommand(command);

        studentRepository.save(student);

        return studentMapper.createdStudentResponseFromStudent(student);
    }
}

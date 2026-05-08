package com.turkcell.library_cqrs.web.controller;

import com.turkcell.library_cqrs.application.features.student.command.create.CreateStudentCommand;
import com.turkcell.library_cqrs.application.features.student.command.create.CreatedStudentResponse;
import com.turkcell.library_cqrs.core.mediator.Mediator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentsController {

    private final Mediator mediator;

    public StudentsController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedStudentResponse create(@RequestBody CreateStudentCommand command) {
        return mediator.send(command);
    }
}

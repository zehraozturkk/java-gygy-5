package com.turkcell.library_cqrs.application.features.student.command.create;

import com.turkcell.library_cqrs.core.mediator.cqrs.Command;

public record CreateStudentCommand(
        String name,
        String surname,
        String phone
) implements Command<CreatedStudentResponse> {}

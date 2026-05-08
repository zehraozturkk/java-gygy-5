package com.turkcell.library_cqrs.application.features.student.command.create;

import com.turkcell.library_cqrs.core.mediator.cqrs.Command;

// Command aynı zamanda DTO görevi görür – ayrı DTO sınıfına gerek yok
public record CreateStudentCommand(
        String name,
        String surname,
        String phone
) implements Command<Integer> {} // döndürülen tip: oluşturulan öğrencinin id'si

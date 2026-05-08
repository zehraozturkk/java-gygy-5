package com.turkcell.library_system.dto;

import java.time.LocalDate;

public record BarrowResponse(
        Integer id,
        Integer studentId,
        String studentFullName,
        Integer officerId,
        String officerFullName,
        Integer bookId,
        String bookName,
        LocalDate barrowDate,
        LocalDate dueDate,
        LocalDate returnDate,
        String status
) {}

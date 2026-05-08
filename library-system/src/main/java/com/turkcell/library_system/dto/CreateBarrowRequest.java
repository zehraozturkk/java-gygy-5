package com.turkcell.library_system.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateBarrowRequest(
        @NotNull(message = "Öğrenci ID boş olamaz") Integer studentId,
        @NotNull(message = "Görevli ID boş olamaz") Integer officerId,
        @NotNull(message = "Kitap ID boş olamaz") Integer bookId,
        @NotNull(message = "Teslim tarihi boş olamaz")
        @FutureOrPresent(message = "Teslim tarihi geçmiş olamaz")
        LocalDate dueDate
) {}

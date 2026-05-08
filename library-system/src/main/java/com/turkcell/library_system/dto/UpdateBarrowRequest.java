package com.turkcell.library_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UpdateBarrowRequest(
        LocalDate returnDate,

        @NotBlank(message = "Durum boş olamaz")
        @Size(max = 20)
        String status
) {}

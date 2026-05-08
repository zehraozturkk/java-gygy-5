package com.turkcell.library_system.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateFineRequest(
        @NotNull(message = "Ödünç ID boş olamaz") Integer barrowId,
        @NotNull(message = "Görevli ID boş olamaz") Integer officerId,
        @NotNull(message = "Tutar boş olamaz")
        @DecimalMin(value = "0.01", message = "Tutar 0'dan büyük olmalıdır")
        BigDecimal amount
) {}

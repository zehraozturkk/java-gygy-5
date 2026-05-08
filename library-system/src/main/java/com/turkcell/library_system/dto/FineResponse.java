package com.turkcell.library_system.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FineResponse(
        Integer id,
        Integer barrowId,
        String studentFullName,
        Integer officerId,
        String officerFullName,
        BigDecimal amount,
        LocalDate issuedDate,
        boolean isPaid
) {}

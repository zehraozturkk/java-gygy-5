package com.turkcell.library_system.exception;

import java.util.List;

// Validation hata yanıt gövdesi
public record ValidationErrorResponse(String argument, List<String> messages) {
}

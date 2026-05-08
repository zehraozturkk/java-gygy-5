package com.turkcell.library_system.exception;

// Hata yanıt gövdesi (genel)
public record ErrorResponse(String title, String type, String message) {
}

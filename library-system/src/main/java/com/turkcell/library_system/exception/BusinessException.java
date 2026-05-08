package com.turkcell.library_system.exception;

// Tüm iş mantığı hatalarının üst sınıfı
public abstract class BusinessException extends RuntimeException {
    protected BusinessException(String message) {
        super(message);
    }
}

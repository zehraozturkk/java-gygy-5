package com.turkcell.library_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Tüm iş mantığı hataları → 400
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBusinessException(BusinessException ex) {
        return new ErrorResponse(
                "İş Mantığı Hatası",
                ex.getClass().getSimpleName(),
                ex.getMessage()
        );
    }

    // Kayıt bulunamadı → 404
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleEntityNotFound(EntityNotFoundException ex) {
        return new ErrorResponse(
                "Kayıt Bulunamadı",
                "EntityNotFoundException",
                ex.getMessage()
        );
    }

    // Bean Validation hataları → 422
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public List<ValidationErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        // alan adına göre grupla
        Map<String, List<String>> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())
                ));

        return fieldErrors.entrySet().stream()
                .map(e -> new ValidationErrorResponse(e.getKey(), e.getValue()))
                .toList();
    }

    // Beklenmeyen hatalar → 500
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleUnexpected(Exception ex) {
        return new ErrorResponse(
                "Sunucu Hatası",
                "InternalServerError",
                "Beklenmeyen bir hata oluştu."
        );
    }
}

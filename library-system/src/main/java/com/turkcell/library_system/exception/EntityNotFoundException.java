package com.turkcell.library_system.exception;

// Kaynak bulunamadığında (404)
public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException(String entityName, Object id) {
        super(entityName + " bulunamadı. Id: " + id);
    }
}

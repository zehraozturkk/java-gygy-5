package com.turkcell.library_system.exception;

// Kullanıcı adı zaten kayıtlı
public class UsernameAlreadyExistsException extends BusinessException {
    public UsernameAlreadyExistsException(String username) {
        super("Bu kullanıcı adı zaten kullanılıyor: " + username);
    }
}

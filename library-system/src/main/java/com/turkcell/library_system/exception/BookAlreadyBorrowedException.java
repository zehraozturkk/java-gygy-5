package com.turkcell.library_system.exception;

// Kitap zaten ödünçte – iade edilmeden tekrar ödünç alınamaz
public class BookAlreadyBorrowedException extends BusinessException {
    public BookAlreadyBorrowedException(Integer bookId) {
        super("Bu kitap zaten ödünçte: bookId=" + bookId);
    }
}

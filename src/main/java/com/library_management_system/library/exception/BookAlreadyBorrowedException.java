package com.library_management_system.library.exception;

public class BookAlreadyBorrowedException extends RuntimeException {
    public BookAlreadyBorrowedException(String message) {
        super(message);
    }
}

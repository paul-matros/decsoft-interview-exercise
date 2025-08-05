package com.pl.example.book.exception;

import com.pl.example.shared.exception.EntityNotFoundException;

public class BookNotFoundException extends EntityNotFoundException {
    public BookNotFoundException(String isbn) {
        super("Book", isbn);
    }

    public BookNotFoundException(String isbn, String context) {
        super("Book", isbn, context);
    }
}
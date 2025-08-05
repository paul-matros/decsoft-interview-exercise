package com.pl.example.author.exception;

import com.pl.example.shared.exception.EntityNotFoundException;

public class AuthorNotFoundException extends EntityNotFoundException {
    public AuthorNotFoundException(Long id) {
        super("Author", id);
    }

    public AuthorNotFoundException(Long id, String context) {
        super("Author", id, context);
    }
}
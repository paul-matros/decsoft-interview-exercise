package com.pl.example.order.exception;

import com.pl.example.shared.exception.EntityNotFoundException;

public class CustomerNotFoundException extends EntityNotFoundException {
    public CustomerNotFoundException(Long id) {
        super("Customer", id);
    }

    public CustomerNotFoundException(Long id, String context) {
        super("Customer", id, context);
    }
}
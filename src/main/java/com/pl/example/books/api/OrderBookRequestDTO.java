package com.pl.example.books.api;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class OrderBookRequestDTO {

    @NotEmpty
    private String isbn;

    @NotNull
    @Positive
    private Long quantity;
}

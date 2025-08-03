package com.pl.example.order.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class OrderBookRequestDTO {

    @NotEmpty
    private String isbn;

    @NotNull
    @Positive
    private Long quantity;
}

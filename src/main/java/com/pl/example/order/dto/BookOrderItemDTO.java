package com.pl.example.order.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class BookOrderItemDTO {

    @NotEmpty
    private String isbn;

    @NotNull
    @Positive
    private Long quantity;
}

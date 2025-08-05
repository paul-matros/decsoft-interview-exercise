package com.pl.example.order.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreateOrderDTO {

    @NotEmpty
    private String isbn;

    @NotNull
    @Positive
    private Integer quantity;

    @NotNull
    @Positive
    private Long customerId;
}

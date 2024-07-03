package com.pl.example.books.api;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class BookOrderDTO {

    @NotNull
    private Long customerId;

    private List<BookOrderItemDTO> orderItems;
}

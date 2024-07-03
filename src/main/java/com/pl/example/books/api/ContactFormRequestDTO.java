package com.pl.example.books.api;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ContactFormRequestDTO {

    @Size(min = 3, max = 70)
    @NotEmpty
    private String subject;

    @NotEmpty
    private String message;
}

package com.pl.example.author.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ContactFormResponseDTO {

    @NotNull
    @NotEmpty
    private String subject;

    @Size(min = 3, max = 70)
    @NotNull
    @NotEmpty
    private String message;

    @NotNull
    private String author;
}

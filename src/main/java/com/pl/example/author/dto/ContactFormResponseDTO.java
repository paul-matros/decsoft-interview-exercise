package com.pl.example.author.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ContactFormResponseDTO(
        @NotNull @NotEmpty String subject,
        @Size(min = 3, max = 70) @NotNull @NotEmpty String message,
        @NotNull String author
) {
}
package com.pl.example.author.dto;

public record ContactFormResponseDTO(
        String subject,
        String message,
        String author
) {
}
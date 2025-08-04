package com.pl.example.author.dto;

public record AuthorDTO(
        Long id,
        String firstName,
        String lastName,
        String bio,
        String email
) {
}

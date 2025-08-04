package com.pl.example.book.dto;

import java.time.LocalDate;

public record BookDTO(
        Long id,
        String title,
        String isbn,
        LocalDate publishedDate,
        String author
) {
}
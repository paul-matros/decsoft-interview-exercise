package com.pl.example.book.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder(toBuilder = true)
public class BookDTO {
    private Long id;
    private String title;
    private String isbn;
    private LocalDate publishedDate;
    private String author;
}

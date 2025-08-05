package com.pl.example.book.controller;

import com.pl.example.book.dto.BookDTO;
import com.pl.example.book.mapper.BookMapper;
import com.pl.example.book.service.BookService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/books")
@RequiredArgsConstructor
@Validated
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<BookDTO> getAll() {
        return bookService.getAllBooks().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public BookDTO get(@PathVariable @Positive Long id) {
        return bookService.getBook(id)
                .map(bookMapper::toDto)
                .orElse(null);
    }
}
package com.pl.example.book.controller;

import com.pl.example.book.dto.BookDTO;
import com.pl.example.book.mapper.BookMapper;
import com.pl.example.book.repository.BookRepository;
import com.pl.example.order.mapper.BookOrderMapper;
import com.pl.example.order.repository.BookOrderRepository;
import com.pl.example.order.repository.CustomerRepository;
import com.pl.example.order.service.BookOrderService;
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

    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;
    private final BookOrderService bookOrderService;
    private final BookOrderRepository bookOrderRepository;
    private final BookMapper bookMapper;
    private final BookOrderMapper bookOrderMapper;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<BookDTO> getAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::mapBookToDTO)
                .toList();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public BookDTO get(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::mapBookToDTO)
                .orElse(null);
    }
}
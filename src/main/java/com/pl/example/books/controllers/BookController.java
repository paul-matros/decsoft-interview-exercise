package com.pl.example.books.controllers;

import com.pl.example.books.api.BookDTO;
import com.pl.example.books.api.BookOrderDTO;
import com.pl.example.books.api.CreateOrderDTO;
import com.pl.example.books.data.BookMapper;
import com.pl.example.books.data.BookOrderMapper;
import com.pl.example.books.data.BookOrderReportItem;
import com.pl.example.books.data.BookOrderRepository;
import com.pl.example.books.data.BookRepository;
import com.pl.example.books.data.CustomerRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/books")
@RequiredArgsConstructor
@Validated
public class BookController {

    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;
    private final BookOrderRepository bookOrderRepository;
    private final BookMapper bookMapper;
    private final BookOrderMapper bookOrderMapper;

    @GetMapping
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<BookDTO> getAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::mapBookToDTO)
                .toList();
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public BookDTO get(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::mapBookToDTO)
                .orElse(null);
    }

    @GetMapping(path = "/orders")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<BookOrderReportItem> getOrdersBetweenDates(@RequestParam @NotNull LocalDate dateFrom, @RequestParam @NotNull LocalDate dateTo) {
        return bookOrderRepository.getBookOrdersByDate(dateFrom, dateTo);
    }

    @PostMapping(path = "/order")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public BookOrderDTO createOrder(@Valid @NotNull @RequestBody CreateOrderDTO createOrderDTO) {
        var newOrder = bookOrderMapper.mapToOrder(createOrderDTO, bookRepository, customerRepository);
        bookOrderRepository.save(newOrder);
        return bookOrderMapper.mapOrderToDTO(newOrder);
    }
}
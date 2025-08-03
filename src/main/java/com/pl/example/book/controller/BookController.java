package com.pl.example.book.controller;

import com.pl.example.book.dto.BookDTO;
import com.pl.example.order.dto.BookOrderDTO;
import com.pl.example.order.dto.CreateOrderDTO;
import com.pl.example.book.mapper.BookMapper;
import com.pl.example.order.mapper.BookOrderMapper;
import com.pl.example.order.dto.BookOrderReportItem;
import com.pl.example.order.repository.BookOrderRepository;
import com.pl.example.book.repository.BookRepository;
import com.pl.example.order.repository.CustomerRepository;
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
    @ResponseStatus(value = HttpStatus.OK)//todo move to separate controller
    public List<BookOrderReportItem> getOrdersBetweenDates(@RequestParam @NotNull LocalDate dateFrom, @RequestParam @NotNull LocalDate dateTo) {
        return bookOrderRepository.getBookOrdersByDate(dateFrom, dateTo);
    }

    @PostMapping(path = "/order")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)//todo move to separate controller
    public BookOrderDTO createOrder(@Valid @NotNull @RequestBody CreateOrderDTO createOrderDTO) {
        var newOrder = bookOrderMapper.mapToOrder(createOrderDTO, bookRepository, customerRepository);
        bookOrderRepository.save(newOrder);
        return bookOrderMapper.mapOrderToDTO(newOrder);
    }
}
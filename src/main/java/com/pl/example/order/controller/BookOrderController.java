package com.pl.example.order.controller;

import com.pl.example.order.dto.BookOrderDTO;
import com.pl.example.order.dto.BookOrderReportItem;
import com.pl.example.order.dto.CreateOrderDTO;
import com.pl.example.order.mapper.BookOrderMapper;
import com.pl.example.order.model.BookOrder;
import com.pl.example.order.service.BookOrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/api/orders")
@RequiredArgsConstructor
@Validated
public class BookOrderController {

    private final BookOrderService bookOrderService;
    private final BookOrderMapper bookOrderMapper;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<BookOrderReportItem> getOrdersBetweenDates(
            @RequestParam @NotNull LocalDate dateFrom,
            @RequestParam @NotNull LocalDate dateTo) {
        return bookOrderService.getOrdersBetweenDates(dateFrom, dateTo);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public BookOrderDTO createOrder(@Valid @NotNull @RequestBody CreateOrderDTO createOrderDTO) {
        BookOrder bookOrder = bookOrderService.createOrder(createOrderDTO);
        return bookOrderMapper.mapOrderToDTO(bookOrder);
    }
}
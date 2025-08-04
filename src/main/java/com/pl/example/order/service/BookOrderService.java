package com.pl.example.order.service;

import com.pl.example.book.model.Book;
import com.pl.example.book.repository.BookRepository;
import com.pl.example.order.dto.BookOrderReportItem;
import com.pl.example.order.dto.CreateOrderDTO;
import com.pl.example.order.mapper.BookOrderMapper;
import com.pl.example.order.model.BookOrder;
import com.pl.example.order.model.Customer;
import com.pl.example.order.repository.BookOrderRepository;
import com.pl.example.order.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookOrderService {

    private final BookOrderRepository bookOrderRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final BookOrderMapper bookOrderMapper;

    @Transactional(readOnly = true)
    public List<BookOrderReportItem> getOrdersBetweenDates(LocalDate dateFrom, LocalDate dateTo) {
        return bookOrderRepository.getBookOrdersByDate(dateFrom, dateTo);
    }

    @Transactional
    public BookOrder createOrder(CreateOrderDTO createOrderDTO) {
        Customer customer = customerRepository.findById(createOrderDTO.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + createOrderDTO.getCustomerId())); //todo custom error?

        Book book = bookRepository.findByIsbn(createOrderDTO.getIsbn())
                .orElseThrow(() -> new IllegalArgumentException("Book not found with ISBN: " + createOrderDTO.getIsbn()));//todo custom error?

        BookOrder newOrder = bookOrderMapper.mapToOrder(createOrderDTO, customer, book);

        // Save and return DTO
        return bookOrderRepository.save(newOrder);
    }
}
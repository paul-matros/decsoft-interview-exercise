package com.pl.example.order.service;

import com.pl.example.book.model.Book;
import com.pl.example.book.repository.BookRepository;
import com.pl.example.order.dto.BookOrderReportItem;
import com.pl.example.order.dto.CreateOrderDTO;
import com.pl.example.order.dto.CreateOrderItemDTO;
import com.pl.example.order.mapper.BookOrderItemMapper;
import com.pl.example.order.mapper.BookOrderMapper;
import com.pl.example.order.model.BookOrder;
import com.pl.example.order.model.BookOrderItem;
import com.pl.example.order.model.Customer;
import com.pl.example.order.repository.BookOrderRepository;
import com.pl.example.order.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookOrderService {

    private final BookOrderRepository bookOrderRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final BookOrderItemMapper bookOrderItemMapper;
    private final BookOrderMapper bookOrderMapper;

    @Transactional(readOnly = true)
    public List<BookOrderReportItem> getOrdersBetweenDates(LocalDate dateFrom, LocalDate dateTo) {
        return bookOrderRepository.getBookOrdersByDate(dateFrom, dateTo);
    }

    public BookOrder createOrder(CreateOrderDTO createOrderDTO) {
        Customer customer = customerRepository.findById(createOrderDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("customer not found: " + createOrderDTO.getCustomerId()));//todo add custom exception
        BookOrder order = bookOrderMapper.toEntity(createOrderDTO, customer);

        // Create and link order items
        List<BookOrderItem> orderItems = new ArrayList<>();
        for (CreateOrderItemDTO itemDTO : createOrderDTO.getOrderItems()) {
            Book book = bookRepository.findByIsbn(itemDTO.getIsbn())
                    .orElseThrow(() -> new RuntimeException("Book not found: " + itemDTO.getIsbn()));//todo add custom exception

            BookOrderItem item = bookOrderItemMapper.toEntity(itemDTO, book, order);
            orderItems.add(item);
        }

        order.setOrderItems(orderItems);
        return bookOrderRepository.save(order);
    }
}
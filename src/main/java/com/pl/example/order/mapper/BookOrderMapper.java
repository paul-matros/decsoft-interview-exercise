package com.pl.example.order.mapper;

import com.pl.example.book.model.Book;
import com.pl.example.order.dto.BookOrderDTO;
import com.pl.example.order.dto.BookOrderItemDTO;
import com.pl.example.order.dto.CreateOrderDTO;
import com.pl.example.order.model.BookOrder;
import com.pl.example.order.model.BookOrderItem;
import com.pl.example.order.model.Customer;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BookOrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", source = "customer")
    @Mapping(target = "orderDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "orderItems", ignore = true)
        // We'll handle this in @AfterMapping
    BookOrder mapToOrder(CreateOrderDTO createOrderDTO, Customer customer, Book book);

    @Mapping(target = "customerId", source = "customer.id")
    BookOrderDTO mapOrderToDTO(BookOrder order);

    @Mapping(target = "isbn", source = "book.isbn")
    BookOrderItemDTO mapOrderItemToDTO(BookOrderItem orderItem);

    @AfterMapping
    default void linkOrderItems(@MappingTarget BookOrder order, CreateOrderDTO createOrderDTO, Customer customer, Book book) {
        BookOrderItem item = new BookOrderItem();
        item.setBook(book);
        item.setQuantity(createOrderDTO.getQuantity());
        item.setOrder(order);

        order.setOrderItems(List.of(item));
    }
}
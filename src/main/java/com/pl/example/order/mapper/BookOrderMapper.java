package com.pl.example.order.mapper;

import com.pl.example.book.model.Book;
import com.pl.example.book.repository.BookRepository;
import com.pl.example.order.dto.BookOrderDTO;
import com.pl.example.order.dto.BookOrderItemDTO;
import com.pl.example.order.dto.CreateOrderDTO;
import com.pl.example.order.model.BookOrder;
import com.pl.example.order.model.BookOrderItem;
import com.pl.example.order.model.Customer;
import com.pl.example.order.repository.CustomerRepository;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BookOrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", source = ".", qualifiedByName = "mapCustomer")
    @Mapping(target = "orderDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "orderItems", source = ".", qualifiedByName = "createOrderItem")
    BookOrder mapToOrder(CreateOrderDTO createOrderDTO,
                         @Context BookRepository bookRepository,
                         @Context CustomerRepository customerRepository);

    @Mapping(target = "customerId", source = "customer.id")
    BookOrderDTO mapOrderToDTO(BookOrder order);

    @Mapping(target = "isbn", source = "book.isbn")
    BookOrderItemDTO mapOrderItemToDTO(BookOrderItem orderItem);

    @Named("mapCustomer")
    default Customer mapCustomer(CreateOrderDTO dto, @Context CustomerRepository customerRepository) {
        return customerRepository.findById(dto.getCustomerId()).orElse(null);
    }

    @Named("createOrderItem")
    default List<BookOrderItem> createOrderItem(CreateOrderDTO dto, @Context BookRepository bookRepository) {
        Book book = bookRepository.findByIsbn(dto.getIsbn()).orElse(null);
        if (book == null) return List.of();

        BookOrderItem item = new BookOrderItem();
        item.setBook(book);
        item.setQuantity(dto.getQuantity());
        return List.of(item);
    }

    @AfterMapping
    default void linkOrderItems(@MappingTarget BookOrder order, CreateOrderDTO createOrderDTO) {
        order.getOrderItems().forEach(item -> item.setOrder(order));
    }
}

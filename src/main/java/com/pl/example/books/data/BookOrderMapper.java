package com.pl.example.books.data;

import com.pl.example.books.api.BookOrderDTO;
import com.pl.example.books.api.BookOrderItemDTO;
import com.pl.example.books.api.CreateOrderDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BookOrderMapper {

    default BookOrder mapToOrder(CreateOrderDTO createOrderDTO, BookRepository bookRepository, CustomerRepository customerRepository) {
        var order = new BookOrder();
        order.setOrderDate(LocalDateTime.now());
        customerRepository.findById(createOrderDTO.getCustomerId())
                .ifPresent(order::setCustomer);
        var orderItem = new BookOrderItem();
        bookRepository.findByIsbn(createOrderDTO.getIsbn())
                .ifPresent(orderItem::setBook);
        orderItem.setQuantity(createOrderDTO.getQuantity());
        orderItem.setOrder(order);
        order.setOrderItems(List.of(orderItem));
        return order;
    }

    default BookOrderDTO mapOrderToDTO(BookOrder order) {
        var orderDTO = new BookOrderDTO();
        orderDTO.setCustomerId(order.getCustomer().getId());
        var orderItemsDTO = new ArrayList<BookOrderItemDTO>();
        for (var orderItem : order.getOrderItems()) {
            var orderItemDTO = new BookOrderItemDTO();
            orderItemDTO.setQuantity(orderItem.getQuantity());
            orderItemDTO.setIsbn(orderItem.getBook().getIsbn());
            orderItemsDTO.add(orderItemDTO);
        }
        orderDTO.setOrderItems(orderItemsDTO);
        return orderDTO;
    }
}
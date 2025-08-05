package com.pl.example.order.mapper;

import com.pl.example.book.model.Book;
import com.pl.example.order.dto.BookOrderItemDTO;
import com.pl.example.order.dto.CreateOrderItemDTO;
import com.pl.example.order.model.BookOrder;
import com.pl.example.order.model.BookOrderItem;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BookOrderItemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "book", source = "book")
    @Mapping(target = "order", source = "order")
    @Mapping(target = "quantity", source = "createOrderItemDTO.quantity")
    BookOrderItem toEntity(CreateOrderItemDTO createOrderItemDTO, Book book, BookOrder order);

    @Mapping(target = "isbn", source = "book.isbn")
    BookOrderItemDTO toDto(BookOrderItem orderItem);
}
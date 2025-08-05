package com.pl.example.order.mapper;

import com.pl.example.order.dto.BookOrderDTO;
import com.pl.example.order.dto.CreateOrderDTO;
import com.pl.example.order.model.BookOrder;
import com.pl.example.order.model.Customer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {BookOrderItemMapper.class})
public interface BookOrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", source = "customer")
    @Mapping(target = "orderDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "orderItems", ignore = true)
    BookOrder toEntity(CreateOrderDTO createOrderDTO, Customer customer);

    @Mapping(target = "customerId", source = "customer.id")
    BookOrderDTO toDto(BookOrder order);
}
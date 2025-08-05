package com.pl.example.order.dto;

import java.util.List;

public record BookOrderDTO(Long customerId,
                           List<BookOrderItemDTO> orderItems) {
}
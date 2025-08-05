package com.pl.example.order.model;

import com.pl.example.shared.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book_order")
@Getter
@Setter
@NoArgsConstructor
public class BookOrder extends BaseEntity {

    @NotNull(message = "Order date is required")
    @Column(name = "order_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime orderDate;

    @NotNull(message = "Customer is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookOrderItem> orderItems = new ArrayList<>();
}
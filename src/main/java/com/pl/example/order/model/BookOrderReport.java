package com.pl.example.order.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;

@Entity
@Table(name = "book_order_report")
@Immutable
@Getter
@NoArgsConstructor
public class BookOrderReport {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "title")
    private String title;

    @Column(name = "order_date", columnDefinition = "DATE")
    private LocalDate orderDate;

    @Column(name = "quantity")
    private Integer quantity;
}
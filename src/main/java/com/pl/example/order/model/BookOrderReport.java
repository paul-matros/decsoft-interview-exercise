package com.pl.example.order.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "book_order_report")
@Immutable
@Getter
public class BookOrderReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
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
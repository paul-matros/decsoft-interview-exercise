package com.pl.example.book.model;

import com.pl.example.author.model.Author;
import com.pl.example.shared.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
public class Book extends BaseEntity {

    @NotBlank(message = "Title is required")
    @Size(max = 256, message = "Title cannot exceed 256 characters")
    @Column(name = "title", nullable = false, length = 256)
    private String title;

    @NotBlank(message = "ISBN is required")
    @Pattern(regexp = "^[0-9]{13}$", message = "ISBN must be exactly 13 digits")
    @Column(name = "isbn", nullable = false, length = 13, unique = true)
    private String isbn;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Author is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private Author author;

    @DecimalMin(value = "0.01", message = "Price must be positive")
    @Column(name = "price", precision = 8, scale = 2)
    private BigDecimal price;
}
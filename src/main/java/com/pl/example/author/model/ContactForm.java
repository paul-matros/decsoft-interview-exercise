package com.pl.example.author.model;

import com.pl.example.shared.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "contact_form")
@Getter
@Setter
@NoArgsConstructor
public class ContactForm extends BaseEntity {

    @NotBlank(message = "Subject is required")
    @Size(max = 64, message = "Subject cannot exceed 64 characters")
    @Column(name = "subject", nullable = false, length = 64)
    private String subject;

    @NotBlank(message = "Message is required")
    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt; //todo make it automatic

    @NotNull(message = "Author is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private Author author;
}
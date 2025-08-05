package com.pl.example.author.model;

import com.pl.example.shared.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "author")
@Getter
@Setter
@NoArgsConstructor
public class Author extends BaseEntity {

    @NotBlank(message = "First name is required")
    @Size(max = 256, message = "First name cannot exceed 256 characters")
    @Column(name = "first_name", nullable = false, length = 256)
    private String firstName;

    @Size(max = 256, message = "Last name cannot exceed 256 characters")
    @Column(name = "last_name", length = 256)
    private String lastName;

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;

    @Email(message = "Email should be valid")
    @Size(max = 256, message = "Email cannot exceed 256 characters")
    @Column(name = "email", length = 256)
    private String email;
}

package com.pl.example.author.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder(toBuilder = true)
public class AuthorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
    private String email;
}

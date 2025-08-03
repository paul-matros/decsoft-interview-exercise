package com.pl.example.author.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UpdateAuthorDTO {
    private String firstName;
    private String lastName;
    private String bio;
    private String email;
}

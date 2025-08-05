package com.pl.example.author.controller;

import com.pl.example.author.dto.AuthorDTO;
import com.pl.example.author.dto.ContactFormRequestDTO;
import com.pl.example.author.dto.ContactFormResponseDTO;
import com.pl.example.author.dto.UpdateAuthorDTO;
import com.pl.example.author.mapper.AuthorMapper;
import com.pl.example.author.mapper.ContactFormMapper;
import com.pl.example.author.model.ContactForm;
import com.pl.example.author.service.AuthorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/authors")
@RequiredArgsConstructor
@Validated
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;
    private final ContactFormMapper contactFormMapper;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<AuthorDTO> getAll() {
        return authorService.getAllAuthors().stream()
                .map(authorMapper::mapAuthorToDTO)
                .toList();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public AuthorDTO get(@PathVariable @Positive Long id) {
        return authorService.getAuthorById(id)
                .map(authorMapper::mapAuthorToDTO)
                .orElse(null);
    }

    @PatchMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public AuthorDTO update(@PathVariable @Positive Long id,
                            @Valid @RequestBody UpdateAuthorDTO updateAuthorDTO) {
        return authorService.updateAuthor(id, updateAuthorDTO)
                .map(authorMapper::mapAuthorToDTO)
                .orElse(null);
    }

    @PostMapping(path = "/{id}/contact")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ContactFormResponseDTO contact(@PathVariable @Positive Long id,
                                          @Valid @RequestBody ContactFormRequestDTO contactFormDTO) {
        ContactForm contactForm = authorService.createContactForm(id, contactFormDTO);
        return contactFormMapper.toDto(contactForm);
    }
}
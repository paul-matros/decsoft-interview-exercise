package com.pl.example.author.controller;

import com.pl.example.author.dto.AuthorDTO;
import com.pl.example.author.dto.ContactFormRequestDTO;
import com.pl.example.author.dto.ContactFormResponseDTO;
import com.pl.example.author.dto.UpdateAuthorDTO;
import com.pl.example.author.mapper.AuthorMapper;
import com.pl.example.author.repository.AuthorRepository;
import com.pl.example.author.repository.ContactFormRepository;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final ContactFormRepository contactFormRepository;
    private final AuthorMapper authorMapper;

    @GetMapping
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<AuthorDTO> getAll() {
        return authorRepository.findAll().stream()
                .map(authorMapper::mapAuthorToDTO)
                .toList();
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public AuthorDTO get(@PathVariable Long id) {
        return authorRepository.findById(id)
                .map(authorMapper::mapAuthorToDTO)
                .orElse(null);
    }

    @PostMapping(path = "/{id}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public AuthorDTO update(@PathVariable Long id, @Valid @RequestBody UpdateAuthorDTO updateAuthorDTO) {
        return authorRepository.findById(id).stream()
                .peek(author -> authorMapper.updateAuthor(author, updateAuthorDTO))
                .map(authorRepository::save)
                .map(authorMapper::mapAuthorToDTO)
                .findFirst()
                .orElse(null);
    }

    @PostMapping(path = "/{id}/contact")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public ContactFormResponseDTO contact(@PathVariable Long id, @Valid @RequestBody ContactFormRequestDTO contactFormDTO) {
        var author = authorRepository.findById(id).orElseThrow();
        var contactForm = authorMapper.mapContactFormDTO(contactFormDTO, author);
        contactFormRepository.save(contactForm);
        return authorMapper.mapContactFormToDTO(contactForm);
    }
}

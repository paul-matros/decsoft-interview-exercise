package com.pl.example.author.service;

import com.pl.example.author.dto.ContactFormRequestDTO;
import com.pl.example.author.dto.UpdateAuthorDTO;
import com.pl.example.author.mapper.AuthorMapper;
import com.pl.example.author.mapper.ContactFormMapper;
import com.pl.example.author.model.Author;
import com.pl.example.author.model.ContactForm;
import com.pl.example.author.repository.AuthorRepository;
import com.pl.example.author.repository.ContactFormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final ContactFormRepository contactFormRepository;
    private final ContactFormMapper contactFormMapper;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public Optional<Author> updateAuthor(Long id, UpdateAuthorDTO updateAuthorDTO) {
        return authorRepository.findById(id)
                .map(author -> {
                    authorMapper.updateAuthor(author, updateAuthorDTO);
                    return authorRepository.save(author);
                });
    }

    public ContactForm createContactForm(Long authorId, ContactFormRequestDTO contactFormDTO) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + authorId));//todo add custom exception?
        ContactForm contactForm = contactFormMapper.toEntity(contactFormDTO, author);
        return contactFormRepository.save(contactForm);
    }
}
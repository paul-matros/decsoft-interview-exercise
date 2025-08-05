package com.pl.example.author.mapper;

import com.pl.example.author.dto.ContactFormRequestDTO;
import com.pl.example.author.dto.ContactFormResponseDTO;
import com.pl.example.author.model.Author;
import com.pl.example.author.model.ContactForm;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ContactFormMapper {

    @Mapping(target = "author", source = "author")
    ContactForm mapContactFormDTO(ContactFormRequestDTO contactForm, Author author);

    @Mapping(target = "author", expression = "java(contactForm.getAuthor().getFirstName() + ' ' + contactForm.getAuthor().getLastName())")
    ContactFormResponseDTO mapContactFormToDTO(ContactForm contactForm);
}
package com.pl.example.author.mapper;

import com.pl.example.author.dto.AuthorDTO;
import com.pl.example.author.dto.ContactFormRequestDTO;
import com.pl.example.author.dto.ContactFormResponseDTO;
import com.pl.example.author.dto.UpdateAuthorDTO;
import com.pl.example.author.model.Author;
import com.pl.example.author.model.ContactForm;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AuthorMapper {

    AuthorDTO mapAuthorToDTO(Author author);

    ContactForm mapContactFormDTO(ContactFormRequestDTO contactForm, Author author);

    @Mapping(target = "author", expression = "java(contactForm.getAuthor().getFirstName() + ' ' + contactForm.getAuthor().getLastName())")
    ContactFormResponseDTO mapContactFormToDTO(ContactForm contactForm);

    @Mapping(target = "firstName", source = "updateAuthorDTO.firstName", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "lastName", source = "updateAuthorDTO.lastName", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "bio", source = "updateAuthorDTO.bio", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "email", source = "updateAuthorDTO.email", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAuthor(@MappingTarget Author author, UpdateAuthorDTO updateAuthorDTO);
}


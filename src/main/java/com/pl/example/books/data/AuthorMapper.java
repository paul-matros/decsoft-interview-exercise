package com.pl.example.books.data;

import com.pl.example.books.api.AuthorDTO;
import com.pl.example.books.api.ContactFormRequestDTO;
import com.pl.example.books.api.ContactFormResponseDTO;
import com.pl.example.books.api.UpdateAuthorDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AuthorMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "bio", source = "bio")
    @Mapping(target = "email", source = "email")
    AuthorDTO mapAuthorToDTO(Author author);

    @Mapping(target = "message", source = "contactForm.message")
    @Mapping(target = "subject", source = "contactForm.subject")
    @Mapping(target = "author", source = "author")
    ContactForm mapContactFormDTO(ContactFormRequestDTO contactForm, Author author);

    @Mapping(target = "message", source = "message")
    @Mapping(target = "subject", source = "subject")
    @Mapping(target = "author", expression = "java(contactForm.getAuthor().getFirstName() + ' ' + contactForm.getAuthor().getLastName())")
    ContactFormResponseDTO mapContactFormToDTO(ContactForm contactForm);

    @Mapping(target = "firstName", source = "updateAuthorDTO.firstName", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "lastName", source = "updateAuthorDTO.lastName", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "bio", source = "updateAuthorDTO.bio", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "email", source = "updateAuthorDTO.email", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAuthor(@MappingTarget Author author, UpdateAuthorDTO updateAuthorDTO);
}

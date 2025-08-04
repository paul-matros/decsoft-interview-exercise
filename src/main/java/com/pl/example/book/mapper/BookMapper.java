package com.pl.example.book.mapper;

import com.pl.example.book.dto.BookDTO;
import com.pl.example.book.model.Book;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BookMapper {

    @Mapping(target = "author", expression = "java(book.getAuthor().getFirstName() + ' ' + book.getAuthor().getLastName())")
    BookDTO mapBookToDTO(Book book);
}
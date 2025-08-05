package com.pl.example.author.mapper;

import com.pl.example.author.dto.AuthorDTO;
import com.pl.example.author.dto.UpdateAuthorDTO;
import com.pl.example.author.model.Author;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthorMapper {

    AuthorDTO toDto(Author author);

    void updateEntity(@MappingTarget Author author, UpdateAuthorDTO updateAuthorDTO);
}
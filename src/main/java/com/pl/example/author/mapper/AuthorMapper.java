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

    AuthorDTO mapAuthorToDTO(Author author);

    void updateAuthor(@MappingTarget Author author, UpdateAuthorDTO updateAuthorDTO);
}
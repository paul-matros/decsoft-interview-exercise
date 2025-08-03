package com.pl.example.shared.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
@RequiredArgsConstructor
public class JacksonConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        var mapper = new Jackson2ObjectMapperBuilder();
        mapper.visibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
        return mapper.build();
    }
}

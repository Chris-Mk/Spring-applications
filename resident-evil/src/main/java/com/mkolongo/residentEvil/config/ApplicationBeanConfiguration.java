package com.mkolongo.residentEvil.config;

import com.mkolongo.residentEvil.domain.entities.Virus;
import com.mkolongo.residentEvil.domain.models.view.VirusViewModel;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        final Converter<LocalDate, String> localDateToStringConverter = new AbstractConverter<>() {
            @Override
            protected String convert(LocalDate date) {
                return date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
            }
        };

        modelMapper.createTypeMap(Virus.class, VirusViewModel.class)
                .addMappings(mapping ->
                        mapping.using(localDateToStringConverter)
                                .map(Virus::getReleaseDate,
                                        (destination, value) -> destination.setReleaseDate(((String) value))));

        return modelMapper;
    }
}

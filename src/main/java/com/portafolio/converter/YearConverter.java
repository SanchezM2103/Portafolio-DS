package com.portafolio.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Year;
import java.time.YearMonth;

@Converter(autoApply = true)
public class YearConverter implements AttributeConverter<Year, String> {

    @Override
    public String convertToDatabaseColumn(Year value) {
        return value != null ? value.toString() : null;
    }

    @Override
    public Year convertToEntityAttribute(String value) {
        return value != null ? Year.parse(value) : null;
    }
}
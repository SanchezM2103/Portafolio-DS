package com.portafolio.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.YearMonth;

@Converter(autoApply = true)
public class YearMonthConverter
        implements AttributeConverter<YearMonth, String> {

    @Override
    public String convertToDatabaseColumn(YearMonth value) {
        return value != null ? value.toString() : null;
    }

    @Override
    public YearMonth convertToEntityAttribute(String value) {
        return value != null ? YearMonth.parse(value) : null;
    }
}
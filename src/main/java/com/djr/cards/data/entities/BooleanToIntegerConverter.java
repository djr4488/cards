package com.djr.cards.data.entities;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * User: djr4488
 * Date: 3/26/2014
 * Time: 10:34 PM
 */
@Converter
public class BooleanToIntegerConverter implements AttributeConverter<Boolean, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Boolean aBoolean) {
        return aBoolean ? 1 : 0;
    }

    @Override
    public Boolean convertToEntityAttribute(Integer integer) {
        return integer == 1;
    }
}

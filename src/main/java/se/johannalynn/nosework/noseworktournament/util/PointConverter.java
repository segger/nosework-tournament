package se.johannalynn.nosework.noseworktournament.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Converter
public class PointConverter implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> list) {
        return String.join(",", list);
    }

    @Override
    public List<String> convertToEntityAttribute(String joined) {
        return new ArrayList<>(Arrays.asList(joined.split(",")));
    }
}

package com.github.chMatvey.model.converter;

import com.github.chMatvey.model.DroneModel;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter
public class DroneModelConverter implements AttributeConverter<DroneModel, String> {
    @Override
    public String convertToDatabaseColumn(DroneModel model) {
        return model.getValue();
    }

    @Override
    public DroneModel convertToEntityAttribute(String value) {
        return Arrays.stream(DroneModel.values())
                .filter(model -> model.getValue().equals(value))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}

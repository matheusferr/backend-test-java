package br.com.fcamara.teste.dev.entity.converter;

import br.com.fcamara.teste.dev.entity.valueObject.Placa;

import javax.persistence.AttributeConverter;

public class PlacaConverter implements AttributeConverter<Placa, String> {
    @Override
    public String convertToDatabaseColumn(Placa placa) {
        return placa.getPlacaValue();
    }

    @Override
    public Placa convertToEntityAttribute(String s) {
        return new Placa(s);
    }
}

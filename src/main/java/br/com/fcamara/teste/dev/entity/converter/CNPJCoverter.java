package br.com.fcamara.teste.dev.entity.converter;

import br.com.fcamara.teste.dev.entity.valueObject.CNPJ;

import javax.persistence.AttributeConverter;

public class CNPJCoverter implements AttributeConverter<CNPJ, String> {
    @Override
    public String convertToDatabaseColumn(CNPJ cnpj) {
        return cnpj.getCnpj();
    }

    @Override
    public CNPJ convertToEntityAttribute(String s) {
        CNPJ cnpj = new CNPJ();
        cnpj.setCnpj(s);

        return cnpj;
    }
}

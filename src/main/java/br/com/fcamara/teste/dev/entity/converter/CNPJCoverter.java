package br.com.fcamara.teste.dev.entity.converter;

import br.com.fcamara.teste.dev.entity.valueObject.CNPJ;

import javax.persistence.AttributeConverter;

public class CNPJCoverter implements AttributeConverter<CNPJ, String> {
	@Override
	public String convertToDatabaseColumn(CNPJ cnpj) {
		return cnpj.getCnpjValue();
	}

	@Override
	public CNPJ convertToEntityAttribute(String s) {
		return new CNPJ(s);
	}
}

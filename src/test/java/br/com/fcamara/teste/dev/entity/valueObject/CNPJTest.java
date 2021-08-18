package br.com.fcamara.teste.dev.entity.valueObject;

import br.com.fcamara.teste.dev.exception.CNPJInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CNPJTest {
	@Test
	void shouldValidateCNPJValue() {
		String cnpjString = "18781203000128";

		CNPJ cnpj = new CNPJ(cnpjString);

		assertEquals(cnpjString, cnpj.getCnpjValue());
	}

	@Test
	void shouldThrowWithAnInvalidCNPJValue() {
		CNPJ cnpj = new CNPJ();

		String cnpjString = "18781203000127";

		assertThrows(CNPJInvalidoException.class, () -> new CNPJ(cnpjString));
		assertThrows(CNPJInvalidoException.class, () -> cnpj.setCnpj(cnpjString));
		assertThrows(CNPJInvalidoException.class, () -> cnpj.setCnpj("11111111111111"));
		assertThrows(CNPJInvalidoException.class, () -> cnpj.setCnpj("aaaaaaaaaaaaaa"));
	}
}
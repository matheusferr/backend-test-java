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
		String cnpjString = "18781203000127";

		assertThrows(CNPJInvalidoException.class, () -> new CNPJ(cnpjString));
	}

	@Test
	void shouldThrowWithSameDigitCNPJValue() {
		String cnpjString = "11111111111111";

		assertThrows(CNPJInvalidoException.class, () -> new CNPJ(cnpjString));
	}

	@Test
	void shouldThrowWithNonNumericCNPJValue() {
		String cnpjString = "aaaaaaaaaaaaaa";

		assertThrows(CNPJInvalidoException.class, () -> new CNPJ(cnpjString));
	}
}
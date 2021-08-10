package br.com.fcamara.teste.dev.entity;

import br.com.fcamara.teste.dev.exception.TelefoneInvalidoException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TelefoneTest {

	@Test
	void shouldValidatePhoneValue() {
		String telefoneString = "1111111111";

		Telefone telefone = new Telefone(telefoneString);

		assertEquals(telefone.getTelefoneValue(), telefoneString);
	}

	@Test
	void shouldThrowWithInvalidPhoneValue() {
		List<String> telefoneInvalidos = new ArrayList<>(Arrays.asList("111111111", "111111111111"));

		assertThrows(TelefoneInvalidoException.class, () -> new Telefone(telefoneInvalidos.get(0)));

		assertThrows(TelefoneInvalidoException.class, () -> new Telefone(telefoneInvalidos.get(1)));
	}
}
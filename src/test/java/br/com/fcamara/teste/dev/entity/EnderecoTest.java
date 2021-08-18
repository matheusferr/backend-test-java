package br.com.fcamara.teste.dev.entity;

import br.com.fcamara.teste.dev.exception.EnderecoInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class EnderecoTest {

	@Test
	void shouldValidateAddressNumber() {
		Estado testEstado = new Estado("SÃO PAULO");
		Cidade testCidade = new Cidade("SANTOS", testEstado);

		assertThrows(EnderecoInvalidoException.class, () -> new Endereco(
				"AVENIDA CONSELHEIRO NÉBIAS", "-1", testCidade)
		);
	}
}
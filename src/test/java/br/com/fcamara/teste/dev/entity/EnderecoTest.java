package br.com.fcamara.teste.dev.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoTest {

    @Test
    void shouldValidateAddressNumber() {
        Estado testEstado = new Estado("SÃO PAULO");
        Cidade testCidade = new Cidade("SANTOS", testEstado);

        assertThrows(IllegalArgumentException.class, () -> new Endereco(
                "AVENIDA CONSELHEIRO NÉBIAS", "-1", testCidade)
        );

    }
}
package br.com.fcamara.teste.dev.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

        assertThrows(IllegalArgumentException.class, () -> new Telefone(telefoneInvalidos.get(0)));

        assertThrows(IllegalArgumentException.class, () -> new Telefone(telefoneInvalidos.get(1)));
    }
}
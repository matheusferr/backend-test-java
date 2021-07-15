package br.com.fcamara.teste.dev.entity.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CNPJTest {
    @Test
    void shouldValidateCNPJValue(){
        CNPJ cnpj = new CNPJ();

        String cnpjString = "18781203000128";

        cnpj.setCnpj(cnpjString);

        assertEquals(cnpjString, cnpj.getCnpjValue());
    }

    @Test
    void shouldThrowWithAnInvalidCNPJValue(){
        String cnpjString = "18781203000127";

        assertThrows(IllegalArgumentException.class, () -> new CNPJ(cnpjString));
    }

    @Test
    void shouldThrowWithSameDigitCNPJValue(){
        String cnpjString = "11111111111111";

        assertThrows(IllegalArgumentException.class, () -> new CNPJ(cnpjString));
    }

    @Test
    void shouldThrowWithNonNumericCNPJValue(){
        String cnpjString = "aaaaaaaaaaaaaa";

        assertThrows(IllegalArgumentException.class, () -> new CNPJ(cnpjString));
    }
}
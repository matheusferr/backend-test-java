package br.com.fcamara.teste.dev.entity.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CNPJTest {
    @Test
    void shouldValidateCNPJValue(){
        CNPJ cnpj = new CNPJ();

        String cnpjString = "18781203000128";

        cnpj.setCnpj(cnpjString);

        assertEquals(cnpjString, cnpj.getCnpj());
    }

    @Test
    void shouldThrowWithAnInvalidCNPJValue(){
        CNPJ cnpj = new CNPJ();

        String cnpjString = "18781203000127";

        assertThrows(IllegalArgumentException.class, () -> cnpj.setCnpj(cnpjString));
    }

    @Test
    void shouldThrowWithSameDigitCNPJValue(){
        CNPJ cnpj = new CNPJ();

        String cnpjString = "11111111111111";

        assertThrows(IllegalArgumentException.class, () -> cnpj.setCnpj(cnpjString));
    }
}
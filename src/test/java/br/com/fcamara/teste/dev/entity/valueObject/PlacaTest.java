package br.com.fcamara.teste.dev.entity.valueObject;

import br.com.fcamara.teste.dev.exception.PlacaInvalidaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlacaTest {
    @Test
    void shouldValidatePlateValue(){
        String plateString = "ABC1D23";

        Placa placa = new Placa(plateString);

        assertEquals(plateString, placa.getPlacaValue());
    }

    @Test
    void shouldThrowWithAInvalidPlateValue(){
        assertThrows(PlacaInvalidaException.class, () -> new Placa("ABC1D2A"));
    }
}
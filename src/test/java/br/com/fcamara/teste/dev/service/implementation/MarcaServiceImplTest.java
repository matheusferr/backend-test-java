package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Marca;
import br.com.fcamara.teste.dev.repository.MarcaRepository;
import br.com.fcamara.teste.dev.service.definition.MarcaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MarcaServiceImplTest {

    @Mock
    private MarcaRepository marcaRepository;

    @InjectMocks
    private MarcaServiceImpl marcaServiceImpl;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateABrand() {
        Marca testMarca = new Marca("FIAT");

        Mockito.when(marcaRepository.findByNomeMarca("FIAT")).thenReturn(Optional.of(testMarca));

        Marca marca = marcaServiceImpl.findByNomeOrCreate("FIAT");

        assertEquals(marca, testMarca);
    }
}
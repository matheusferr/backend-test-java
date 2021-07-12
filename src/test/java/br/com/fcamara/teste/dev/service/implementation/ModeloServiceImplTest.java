package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Marca;
import br.com.fcamara.teste.dev.entity.Modelo;
import br.com.fcamara.teste.dev.repository.ModeloRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ModeloServiceImplTest {

    @Mock
    private ModeloRepository modeloRepository;

    @InjectMocks
    private ModeloServiceImpl modeloServiceImpl;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateAModel() {
        Marca testMarca = new Marca("FIAT");
        Modelo testModelo = new Modelo("PALIO", testMarca);

        Mockito.when(modeloRepository.findByNomeModelo("PALIO")).thenReturn(Optional.of(testModelo));

        Modelo modelo = modeloServiceImpl.findByNomeModeloOrCreate("PALIO", testMarca);

        assertEquals(modelo, testModelo);
    }
}
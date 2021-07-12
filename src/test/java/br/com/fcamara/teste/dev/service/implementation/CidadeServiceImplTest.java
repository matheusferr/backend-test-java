package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Cidade;
import br.com.fcamara.teste.dev.entity.Estado;
import br.com.fcamara.teste.dev.repository.CidadeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CidadeServiceImplTest {

    @Mock
    private CidadeRepository cidadeRepository;

    @InjectMocks
    private CidadeServiceImpl cidadeServiceImpl;

    @BeforeEach
    void beforeEach(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateACity() {
        Estado testEstado = new Estado("SÃO PAULO");

        Cidade testCidade = new Cidade("SANTOS", testEstado);

        Mockito.when(this.cidadeRepository.findByNomeCidade("SANTOS")).thenReturn(Optional.of(testCidade));

        Cidade cidade = this.cidadeServiceImpl.findByNomeCidadeOrCreate("SANTOS", testEstado);

        assertEquals(cidade, testCidade);
        assertEquals(testEstado, testCidade.getEstado());
    }
}
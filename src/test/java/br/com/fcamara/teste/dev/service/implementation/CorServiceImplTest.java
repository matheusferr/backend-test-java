package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Cor;
import br.com.fcamara.teste.dev.repository.CorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CorServiceImplTest {
    @Mock
    private CorRepository corRepository;

    @InjectMocks
    private CorServiceImpl corServiceImpl;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void shouldCreateAColor() {
        Cor testCor = new Cor("VERMELHO");

        Mockito.when(corRepository.findByNomeCor("VERMELHO")).thenReturn(Optional.of(testCor));

        Cor cor = corServiceImpl.findByNomeOrCreate("VERMELHO");

        assertEquals(cor, testCor);
    }
}
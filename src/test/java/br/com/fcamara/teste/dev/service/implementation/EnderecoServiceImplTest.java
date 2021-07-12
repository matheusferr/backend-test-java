package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Cidade;
import br.com.fcamara.teste.dev.entity.Endereco;
import br.com.fcamara.teste.dev.entity.Estado;
import br.com.fcamara.teste.dev.repository.EnderecoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoServiceImplTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoServiceImpl enderecoServiceImpl;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateAnAddress() {
        Estado testEstado = new Estado("SÃO PAULO");
        Cidade testCidade = new Cidade("SANTOS", testEstado);

        Endereco testEndereco = new Endereco("AVENIDA CONSELHEIRO NÉBIAS", 1, testCidade);

        Mockito.when(enderecoRepository.findByLogradouroAndNumero("AVENIDA CONSELHEIRO NÉBIAS", 1))
                .thenReturn(Optional.of(testEndereco));

        Endereco endereco = this.enderecoServiceImpl.findByLogradouroAndNumero("AVENIDA CONSELHEIRO NÉBIAS", 1);

        assertEquals(endereco, testEndereco);
        assertEquals(endereco.getCidade(), testCidade);
        assertEquals(endereco.getCidade().getEstado(), testEstado);
    }
}
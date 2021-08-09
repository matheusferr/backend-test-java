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
	private Estado testEstado = new Estado("SÃO PAULO");

	private Cidade testCidade = new Cidade("SANTOS", testEstado);

	@Mock
	private CidadeRepository cidadeRepository;

	@InjectMocks
	private CidadeServiceImpl cidadeServiceImpl;

	@BeforeEach
	void beforeEach() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void shouldGetACity() {
		Mockito.when(this.cidadeRepository.findByNomeCidade("SANTOS")).thenReturn(Optional.of(testCidade));

		Optional<Cidade> cidade = this.cidadeServiceImpl.findByNomeCidade("SANTOS");

		assertEquals(cidade.get(), testCidade);
	}

	@Test
	void shouldCreateACity() {
		Mockito.when(this.cidadeRepository.findByNomeCidade("SANTOS")).thenReturn(Optional.empty());

		Mockito.when(this.cidadeRepository.save(testCidade)).thenReturn(testCidade);

		Cidade cidade = this.cidadeServiceImpl.create("SANTOS", testEstado);

		assertEquals(cidade, testCidade);
		assertEquals(testEstado, testCidade.getEstado());
	}
}
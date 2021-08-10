package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Estado;
import br.com.fcamara.teste.dev.repository.EstadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EstadoServiceImplTest {

	@Mock
	private EstadoRepository estadoRepository;

	@InjectMocks
	private EstadoServiceImpl estadoServiceImpl;

	@BeforeEach
	void beforeEach() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void shouldCreateAState() {
		Estado testEstado = new Estado("SÃO PAULO");

		Mockito.when(estadoRepository.findByNomeEstado("SÃO PAULO")).thenReturn(Optional.of(testEstado));

		Estado estado = this.estadoServiceImpl.findByNomeOrCreate("SÃO PAULO");

		assertEquals(estado, testEstado);
	}
}
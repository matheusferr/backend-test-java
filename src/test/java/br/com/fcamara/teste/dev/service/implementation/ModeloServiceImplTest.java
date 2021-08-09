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
	private Marca testMarca = new Marca("FIAT");
	private Modelo testModelo = new Modelo("PALIO", testMarca);

	@Mock
	private ModeloRepository modeloRepository;

	@InjectMocks
	private ModeloServiceImpl modeloServiceImpl;

	@BeforeEach
	void beforeEach() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void shouldGetAModel() {
		Mockito.when(modeloRepository.findByNomeModelo("PALIO")).thenReturn(Optional.of(testModelo));

		Optional<Modelo> modeloOptional = modeloServiceImpl.findByNomeModelo("PALIO");

		assertEquals(modeloOptional.get(), testModelo);
	}

	@Test
	void shouldCreateAModel() {
		Mockito.when(modeloRepository.save(new Modelo("PALIO", testMarca))).thenReturn(testModelo);

		Modelo modelo = modeloServiceImpl.create("PALIO", testMarca);

		assertEquals(modelo, testModelo);
	}
}
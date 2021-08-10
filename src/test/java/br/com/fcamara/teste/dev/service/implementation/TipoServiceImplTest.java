package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Tipo;
import br.com.fcamara.teste.dev.entity.enums.VeiculoTipo;
import br.com.fcamara.teste.dev.repository.TipoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TipoServiceImplTest {
	@Mock
	private TipoRepository tipoRepository;

	@InjectMocks
	private TipoServiceImpl tipoServiceImpl;

	private final Tipo testTipo = new Tipo(VeiculoTipo.CARRO);

	@BeforeEach
	void beforeEach() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void shouldCreateAType() {
		Mockito.when(this.tipoRepository.findByTipo(VeiculoTipo.CARRO)).thenReturn(Optional.empty());
		Mockito.when(this.tipoRepository.save(this.testTipo)).thenReturn(this.testTipo);

		Tipo tipo = this.tipoServiceImpl.findOneOrCreate(VeiculoTipo.CARRO);

		assertEquals(tipo, testTipo);
	}

}
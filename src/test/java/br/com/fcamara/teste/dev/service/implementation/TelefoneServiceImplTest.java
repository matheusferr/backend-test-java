package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Telefone;
import br.com.fcamara.teste.dev.repository.TelefoneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TelefoneServiceImplTest {
	@Mock
	private TelefoneRepository telefoneRepository;

	@InjectMocks
	private TelefoneServiceImpl telefoneServiceImpl;

	private final Telefone testTelefone = new Telefone("1234567890");

	@BeforeEach
	void beforeEach() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void shouldGetAPhone() {
		Mockito.when(this.telefoneRepository.findByNumeroTelefone("1234567890")).thenReturn(
				Optional.of(this.testTelefone)
		);

		Telefone telefone = this.telefoneServiceImpl.findByNumero("1234567890");

		assertEquals(telefone, this.testTelefone);
	}

	@Test
	void shouldCreateAPhone() {
		Mockito.when(this.telefoneRepository.findByNumeroTelefone("1234567890")).thenReturn(Optional.empty());

		Mockito.when(this.telefoneRepository.save(testTelefone)).thenReturn(this.testTelefone);

		Telefone telefone = this.telefoneServiceImpl.findByNumeroOrCreate("1234567890");

		assertEquals(telefone, this.testTelefone);
	}
}
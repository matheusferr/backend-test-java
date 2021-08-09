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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnderecoServiceImplTest {

	@Mock
	private EnderecoRepository enderecoRepository;

	@Mock
	private CidadeServiceImpl cidadeServiceImpl;

	@Mock
	private EstadoServiceImpl estadoServiceImpl;

	@InjectMocks
	private EnderecoServiceImpl enderecoServiceImpl;


	private final Estado testEstado = new Estado("SÃO PAULO");
	private final Cidade testCidade = new Cidade("SANTOS", testEstado);
	private final Endereco testEndereco = new Endereco("AVENIDA CONSELHEIRO NÉBIAS", "1", testCidade);

	@BeforeEach
	void beforeEach() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void shouldGetAnAddress() {
		Mockito.when(enderecoRepository.findByLogradouroAndNumero("AVENIDA CONSELHEIRO NÉBIAS", "1"))
				.thenReturn(Optional.of(testEndereco));

		Endereco endereco = this.enderecoServiceImpl
				.findOrCreate(
						"AVENIDA CONSELHEIRO NÉBIAS", "1", "SANTOS", "SÃO PAULO"
				);

		assertEquals(endereco, testEndereco);
		assertEquals(endereco.getCidade(), testCidade);
		assertEquals(endereco.getCidade().getEstado(), testEstado);
	}

	@Test
	void shouldCreateAnAddress() {
		Mockito.when(enderecoRepository.findByLogradouroAndNumero("AVENIDA CONSELHEIRO NÉBIAS", "1"))
				.thenReturn(Optional.empty());

		Mockito.when(cidadeServiceImpl.findByNomeCidade("SANTOS")).thenReturn(Optional.empty());

		Mockito.when(estadoServiceImpl.findByNomeOrCreate("SÃO PAULO")).thenReturn(testEstado);

		Mockito.when(cidadeServiceImpl.create("SANTOS", testEstado)).thenReturn(testCidade);

		Mockito.when(enderecoRepository.save(testEndereco)).thenReturn(testEndereco);

		Endereco endereco = this.enderecoServiceImpl.findOrCreate(
				"AVENIDA CONSELHEIRO NÉBIAS", "1", "SANTOS", "SÃO PAULO"
		);

		assertEquals(endereco, testEndereco);
		assertEquals(endereco.getCidade(), testCidade);
		assertEquals(endereco.getCidade().getEstado(), testEstado);
	}
}
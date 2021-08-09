package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.*;
import br.com.fcamara.teste.dev.entity.enums.VeiculoTipo;
import br.com.fcamara.teste.dev.entity.valueObject.CNPJ;
import br.com.fcamara.teste.dev.entity.valueObject.Placa;
import br.com.fcamara.teste.dev.exception.OperacaoInvalidaException;
import br.com.fcamara.teste.dev.form.vaga.VagaForm;
import br.com.fcamara.teste.dev.repository.VagaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VagaServiceImplTest {
	@Mock
	private EstabelecimentoServiceImpl estabelecimentoServiceImpl;

	@Mock
	private VeiculoServiceImpl veiculoServiceImpl;

	@Mock
	private VagaRepository vagaRepository;

	@InjectMocks
	private VagaServiceImpl vagaServiceImpl;

	private Estabelecimento testEstabelecimento;

	private Vaga testVaga;

	private VagaForm vagaForm;

	private Veiculo testVeiculo;

	@BeforeEach
	void beforeEach() {
		MockitoAnnotations.openMocks(this);

		Estado testEstado = new Estado("SÃO PAULO");
		Cidade testCidade = new Cidade("SANTOS", testEstado);
		Endereco testEndereco = new Endereco("AVENIDA CONSELHEIRO NÉBIAS", "1", testCidade);

		this.testEstabelecimento = new Estabelecimento("TEST01", new CNPJ("45857255000103"),
				testEndereco, new Telefone("1234567890"), 1, 1);

		Marca testMarca = new Marca("Fiat");
		Modelo testModelo = new Modelo("Palio", testMarca);
		Cor testCor = new Cor("Vermelho");

		this.testVeiculo = new Veiculo(testModelo, testCor, new Placa("BRA1A23"), VeiculoTipo.CARRO);

		vagaForm = new VagaForm();

		vagaForm.setCnpj(testEstabelecimento.getCnpj().getCnpjValue());
		vagaForm.setPlaca(testVeiculo.getPlaca().getPlacaValue());

		testVaga = new Vaga(this.testVeiculo);
	}

	@Test
	void shouldAddAVehicle() {
		Mockito.when(this.veiculoServiceImpl.findByPlaca(this.vagaForm.getPlaca())).thenReturn(this.testVeiculo);
		Mockito.when(this.estabelecimentoServiceImpl.findByCnpj(this.vagaForm.getCnpj())).thenReturn(
				this.testEstabelecimento
		);

		Mockito.when(this.vagaRepository.save(testVaga)).thenReturn(testVaga);

		Vaga vaga = this.vagaServiceImpl.addVehicle(this.vagaForm);

		assertEquals(testVaga, vaga);
	}

	@Test
	void shouldNotAddAVehicle() {
		this.testEstabelecimento.getVagas().add(testVaga);

		Mockito.when(this.veiculoServiceImpl.findByPlaca(this.vagaForm.getPlaca())).thenReturn(this.testVeiculo);
		Mockito.when(this.estabelecimentoServiceImpl.findByCnpj(this.vagaForm.getCnpj())).thenReturn(
				this.testEstabelecimento
		);


		assertThrows(OperacaoInvalidaException.class,
				() -> this.vagaServiceImpl.addVehicle(this.vagaForm));
	}

	@Test
	void shouldRemoveAVehicle() {
		this.testEstabelecimento.getVagas().add(testVaga);

		Mockito.when(this.veiculoServiceImpl.findByPlaca(this.vagaForm.getPlaca())).thenReturn(this.testVeiculo);
		Mockito.when(this.estabelecimentoServiceImpl.findByCnpj(this.vagaForm.getCnpj())).thenReturn(
				this.testEstabelecimento
		);
		Mockito.when(this.vagaRepository.findByVeiculoAndSaidaNull(this.testVeiculo)).thenReturn(Optional.of(testVaga));

		testVaga.setSaida(LocalDateTime.now());

		Mockito.when(this.vagaRepository.save(testVaga)).thenReturn(testVaga);

		Vaga vaga = this.vagaServiceImpl.removeVehicle(this.vagaForm);

		assertEquals(testVaga, vaga);
	}

	@Test
	void shouldNotRemoveAVehicle() {
		Mockito.when(this.veiculoServiceImpl.findByPlaca(this.vagaForm.getPlaca())).thenReturn(this.testVeiculo);
		Mockito.when(this.estabelecimentoServiceImpl.findByCnpj(this.vagaForm.getCnpj())).thenReturn(
				this.testEstabelecimento
		);

		assertThrows(OperacaoInvalidaException.class,
				() -> this.vagaServiceImpl.removeVehicle(this.vagaForm));
	}
}
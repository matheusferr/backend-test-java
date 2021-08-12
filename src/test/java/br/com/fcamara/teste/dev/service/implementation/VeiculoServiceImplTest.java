package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.*;
import br.com.fcamara.teste.dev.entity.enums.VeiculoTipo;
import br.com.fcamara.teste.dev.entity.valueObject.Placa;
import br.com.fcamara.teste.dev.form.veiculo.VeiculoForm;
import br.com.fcamara.teste.dev.form.veiculo.VeiculoUpdateForm;
import br.com.fcamara.teste.dev.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class VeiculoServiceImplTest {
	@Mock
	private MarcaServiceImpl marcaServiceImpl;

	@Mock
	private CorServiceImpl corServiceImpl;

	@Mock
	private ModeloServiceImpl modeloServiceImpl;

	@Mock
	private TipoServiceImpl tipoServiceImpl;

	@Mock
	private VeiculoRepository veiculoRepository;

	@InjectMocks
	private VeiculoServiceImpl veiculoServiceImpl;

	private final Marca testMarca = new Marca("Fiat");
	private final Modelo testModelo = new Modelo("Palio", testMarca);
	private final Cor testCor = new Cor("Vermelho");
	private final Tipo testTipo = new Tipo(VeiculoTipo.CARRO);

	private final List<Veiculo> testVeiculos = new ArrayList<>(List.of(
			new Veiculo(testModelo, testCor, new Placa("BRA1A23"), testTipo),
			new Veiculo(testModelo, testCor, new Placa("BRA4A56"), testTipo)
	));

	@BeforeEach
	void beforeEach() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void shoudReturnAListOfVehicles() {
		Mockito.when(veiculoRepository.findAll()).thenReturn(this.testVeiculos);

		assertEquals(veiculoServiceImpl.index(), this.testVeiculos);
	}

	@Test
	void shouldReturnAVehicleByItsID() {
		Mockito.when(veiculoRepository.findById(1)).thenReturn(Optional.of(this.testVeiculos.get(0)));

		Veiculo encontrado = veiculoServiceImpl.findById(1);

		assertEquals(encontrado, this.testVeiculos.get(0));
	}

	@Test
	void shouldCreateAVehicle() {
		VeiculoForm veiculoForm = new VeiculoForm("Fiat", "Palio", "Vermelho", "BRA1A23",
				"CARRO");

		Mockito.when(this.corServiceImpl.findByNomeOrCreate(veiculoForm.getCor())).thenReturn(testCor);
		Mockito.when(this.tipoServiceImpl.findOneOrCreate(VeiculoTipo.CARRO)).thenReturn(testTipo);
		Mockito.when(this.modeloServiceImpl.findByNomeModelo(veiculoForm.getModelo())).thenReturn(Optional.empty());
		Mockito.when(this.marcaServiceImpl.findByNomeOrCreate(veiculoForm.getMarca())).thenReturn(testMarca);
		Mockito.when(this.modeloServiceImpl.create(veiculoForm.getModelo(), testMarca)).thenReturn(testModelo);

		Veiculo veiculo = veiculoForm.toVeiculo(testModelo, testCor, testTipo);

		Mockito.when(this.veiculoRepository.save(veiculo)).thenReturn(veiculo);

		Veiculo novo = this.veiculoServiceImpl.create(veiculoForm);

		assertEquals(novo, veiculo);
	}

	@Test
	void shouldUpdateAVehicle() {
		Veiculo veiculo = this.testVeiculos.get(0);

		VeiculoUpdateForm veiculoUpdateForm = new VeiculoUpdateForm("Verde");

		Cor cor = new Cor("Verde");

		veiculoUpdateForm.setCor(cor.getNomeCor());

		Mockito.when(this.veiculoRepository.findById(1)).thenReturn(Optional.of(veiculo));

		Mockito.when(this.corServiceImpl.findByNomeOrCreate(veiculoUpdateForm.getCor())).thenReturn(cor);

		veiculo.setCor(cor);

		Mockito.when(this.veiculoRepository.save(veiculo)).thenReturn(veiculo);

		Veiculo atualizado = this.veiculoServiceImpl.update(1, veiculoUpdateForm);

		assertEquals(atualizado.getModelo(), veiculo.getModelo());
		assertEquals(atualizado.getPlaca(), veiculo.getPlaca());
		assertNotEquals(atualizado.getCor(), this.testCor);
	}
}
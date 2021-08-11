package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.*;
import br.com.fcamara.teste.dev.entity.valueObject.CNPJ;
import br.com.fcamara.teste.dev.form.contato.TelefoneForm;
import br.com.fcamara.teste.dev.form.estacionamento.EstacionamentoForm;
import br.com.fcamara.teste.dev.form.estacionamento.EstacionamentoUpdateForm;
import br.com.fcamara.teste.dev.repository.EstabelecimentoRepository;
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

class EstacionamentoServiceImplTest {

	private final List<Estacionamento> testEstacionamentos = new ArrayList<>();

	@Mock
	private EstabelecimentoRepository estabelecimentoRepository;
	@Mock
	private EnderecoServiceImpl enderecoServiceImpl;
	@Mock
	private TelefoneServiceImpl telefoneServiceImpl;
	@InjectMocks
	private EstabelecimentoServiceImpl estabelecimentoServiceImpl;

	@BeforeEach
	void beforeEach() {
		MockitoAnnotations.openMocks(this);

		Estado testEstado = new Estado("SÃO PAULO");
		Cidade testCidade = new Cidade("SANTOS", testEstado);
		Endereco testEndereco = new Endereco("AVENIDA CONSELHEIRO NÉBIAS", "1", testCidade);

		CNPJ cnpj = new CNPJ("45857255000103");

		Telefone telefone = new Telefone("1234567890");

		Estacionamento testEstacionamento = new Estacionamento("TEST01", cnpj, testEndereco,
				telefone, 1, 1);

		this.testEstacionamentos.add(testEstacionamento);

		testEndereco.setNumero("2");

		telefone.setTelefone("12345678901");

		cnpj.setCnpj("24400188000123");

		testEstacionamento = new Estacionamento("TEST02", cnpj, testEndereco, telefone,
				1, 1);

		this.testEstacionamentos.add(testEstacionamento);
	}

	@Test
	void shoudReturnAListOfEstablishments() {
		Mockito.when(this.estabelecimentoRepository.findAll()).thenReturn(this.testEstacionamentos);

		List<Estacionamento> estacionamentos = this.estabelecimentoServiceImpl.index();

		assertEquals(estacionamentos.size(), 2);
		assertEquals(estacionamentos, this.testEstacionamentos);
	}

	@Test
	void shoudReturnAnEstablishmentByItsID() {
		Mockito.when(this.estabelecimentoRepository.findById(1)).thenReturn(Optional.of(this.testEstacionamentos.get(0)));

		Estacionamento estacionamento = this.estabelecimentoServiceImpl.findById(1);

		assertEquals(estacionamento, this.testEstacionamentos.get(0));
	}

	@Test
	void shouldCreateAnEstablishment() {
		EstacionamentoForm estacionamentoForm = new EstacionamentoForm(
				"TEST03", "24400188000123", "AVENIDA CONSELHEIRO NÉBIAS", "2",
				"SANTOS", "SÃO PAULO", "12345678901", 1, 1);
		CNPJ cnpj = new CNPJ(estacionamentoForm.getCnpj());
		Telefone telefone = new Telefone(estacionamentoForm.getTelefone());
		Estado estado = new Estado(estacionamentoForm.getEstado());
		Cidade cidade = new Cidade(estacionamentoForm.getCidade(), estado);
		Endereco endereco = new Endereco(estacionamentoForm.getLogradouro(), estacionamentoForm.getNumero(), cidade);

		Estacionamento testEstacionamento = estacionamentoForm.toEstabelecimento(endereco, cnpj, telefone);

		Mockito.when(this.telefoneServiceImpl.findByNumeroOrCreate(
				estacionamentoForm.getTelefone()
		)).thenReturn(telefone);

		Mockito.when(this.enderecoServiceImpl.findOrCreate(
				estacionamentoForm.getLogradouro(), estacionamentoForm.getNumero(), estacionamentoForm.getCidade(),
				estacionamentoForm.getEstado()
		)).thenReturn(endereco);

		Mockito.when(
				this.estabelecimentoRepository.save(
						estacionamentoForm.toEstabelecimento(endereco, cnpj, telefone)
				)).thenReturn(testEstacionamento);

		Estacionamento estacionamento = this.estabelecimentoServiceImpl.create(estacionamentoForm);

		assertEquals(estacionamento, testEstacionamento);
	}

	@Test
	void shouldUpdateAnEstablishment() {
		Estacionamento baseEstacionamento = this.testEstacionamentos.get(1);

		Estacionamento testEstacionamento = new Estacionamento(baseEstacionamento.getNome(),
				baseEstacionamento.getCnpj(), baseEstacionamento.getEndereco(),
				baseEstacionamento.getTelefones().get(0), baseEstacionamento.getVagasCarro(),
				baseEstacionamento.getVagasMoto());

		EstacionamentoUpdateForm estacionamentoUpdateForm = new EstacionamentoUpdateForm();

		estacionamentoUpdateForm.setNome("TEST03");

		estacionamentoUpdateForm.setVagasCarro(12);

		estacionamentoUpdateForm.setVagasMoto(8);

		Mockito.when(this.estabelecimentoRepository.findById(2))
				.thenReturn(Optional.of(testEstacionamento));

		testEstacionamento.setNome(estacionamentoUpdateForm.getNome());

		Mockito.when(this.estabelecimentoRepository.save(testEstacionamento)).thenReturn(testEstacionamento);

		Estacionamento estacionamentoAtualizado = this.estabelecimentoServiceImpl
				.update(2, estacionamentoUpdateForm);

		assertNotEquals(
				estacionamentoAtualizado.getNome(),
				this.testEstacionamentos.get(1).getNome()
		);
		assertNotEquals(
				estacionamentoAtualizado.getVagasCarro(),
				this.testEstacionamentos.get(1).getVagasCarro()
		);
		assertNotEquals(
				estacionamentoAtualizado.getVagasMoto(),
				this.testEstacionamentos.get(1).getVagasMoto()
		);
		assertEquals(estacionamentoAtualizado.getCnpj(), this.testEstacionamentos.get(1).getCnpj());
	}

	@Test
	void shouldAddAPhone() {
		Estacionamento testEstacionamento = this.testEstacionamentos.get(0);

		TelefoneForm estabelecimentoForm = new TelefoneForm();

		estabelecimentoForm.setTelefone("12345678901");

		Mockito.when(estabelecimentoRepository.findById(1)).thenReturn(Optional.of(testEstacionamento));

		testEstacionamento.getTelefones().add(estabelecimentoForm.toTelefone());

		Mockito.when(estabelecimentoRepository.save(testEstacionamento)).thenReturn(testEstacionamento);

		Estacionamento estacionamento = this.estabelecimentoServiceImpl.addPhone(1, estabelecimentoForm);

		assertEquals(estacionamento.getTelefones().size(), testEstacionamento.getTelefones().size());
	}
}

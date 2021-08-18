package br.com.fcamara.teste.dev.controller;

import br.com.fcamara.teste.dev.dto.*;
import br.com.fcamara.teste.dev.entity.*;
import br.com.fcamara.teste.dev.entity.valueObject.CNPJ;
import br.com.fcamara.teste.dev.exception.CNPJInvalidoException;
import br.com.fcamara.teste.dev.exception.OperacaoInvalidaException;
import br.com.fcamara.teste.dev.form.contato.TelefoneForm;
import br.com.fcamara.teste.dev.form.estacionamento.EstacionamentoForm;
import br.com.fcamara.teste.dev.form.estacionamento.EstacionamentoUpdateForm;
import br.com.fcamara.teste.dev.service.implementation.EstacionamentoServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EstacionamentoController.class)
class EstacionamentoControllerIntegrationTest {

	private final List<Estacionamento> testEstacionamentos = new ArrayList<>();
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private EstacionamentoServiceImpl estacionamentoServiceImpl;

	@BeforeEach
	void beforeEach() {
		Estado testEstado = new Estado("SÃO PAULO");
		Cidade testCidade = new Cidade("SANTOS", testEstado);

		this.testEstacionamentos.add(new Estacionamento("TEST01", new CNPJ("45857255000103"),
				new Endereco("AVENIDA CONSELHEIRO NÉBIAS", "1", testCidade), new Telefone("1234567890"), 1, 1));

		this.testEstacionamentos.add(new Estacionamento("TEST02", new CNPJ("24400188000123"),
				new Endereco("AVENIDA CONSELHEIRO NÉBIAS", "2", testCidade), new Telefone("1234567891"), 1, 1));
	}

	@Test
	void shouldGetAListOfParkingLots() throws Exception {
		Mockito.when(this.estacionamentoServiceImpl.index()).thenReturn(this.testEstacionamentos);
		MvcResult response = mockMvc.perform(get("/estacionamentos")).andExpect(status().isOk()).andReturn();

		String json = response.getResponse().getContentAsString();

		List<EstacionamentoDto> dtos = objectMapper.readValue(json, new TypeReference<>() {
		});

		assertEquals(EstacionamentoDto.convertList(this.testEstacionamentos), dtos);
	}

	@Test
	void shouldGetAParkingLot() throws Exception {
		Estacionamento testEstacionamento = this.testEstacionamentos.get(0);

		Mockito.when(this.estacionamentoServiceImpl.findById(1)).thenReturn(testEstacionamento);

		MvcResult response = mockMvc.perform(get("/estacionamentos/1")).andExpect(status().isOk()).andReturn();

		String json = response.getResponse().getContentAsString(StandardCharsets.UTF_8);

		EstacionamentoDto dto = objectMapper.readValue(json, EstacionamentoDto.class);

		assertEquals(new EstacionamentoDto(testEstacionamento), dto);
	}

	@Test
	void shouldValidateRequestQueryParam() throws Exception {
		MvcResult response = mockMvc.perform(get("/estacionamentos/a")).andExpect(status().isBadRequest())
				.andReturn();

		String json = response.getResponse().getContentAsString(StandardCharsets.UTF_8);

		ErroDto dto = objectMapper.readValue(json, ErroDto.class);

		assertEquals(new ErroDto("Tipo inválido para parâmetro 'id'"), dto);
	}

	@Test
	void shouldGetAParkingLotWithDetails() throws Exception {
		Estacionamento testEstacionamento = this.testEstacionamentos.get(0);

		Mockito.when(this.estacionamentoServiceImpl.findById(1)).thenReturn(testEstacionamento);

		MvcResult response = mockMvc.perform(
				get("/estacionamentos/1").param("detalhes", "true")
		).andExpect(status().isOk()).andReturn();

		String json = response.getResponse().getContentAsString(StandardCharsets.UTF_8);

		EstacionamentoDetalhesDto dto = objectMapper.readValue(json, EstacionamentoDetalhesDto.class);

		EstacionamentoDetalhesDto expected = new EstacionamentoDetalhesDto(testEstacionamento);

		assertEquals(expected, dto);
	}

	@Test
	void shouldCreateAParkingLot() throws Exception {
		Estacionamento testEstacionamento = this.testEstacionamentos.get(0);

		EstacionamentoForm estacionamentoForm = new EstacionamentoForm("TEST01", "45857255000103",
				"AVENIDA CONSELHEIRO NÉBIAS", "1", "SANTOS", "SÃO PAULO",
				"1234567890", 1, 1);

		Mockito.when(this.estacionamentoServiceImpl.create(Mockito.any(EstacionamentoForm.class)))
				.thenReturn(testEstacionamento);

		MvcResult response = mockMvc.perform(
				post("/estacionamentos")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(estacionamentoForm))
		).andExpect(status().isCreated()).andReturn();

		String json = response.getResponse().getContentAsString(StandardCharsets.UTF_8);

		EstacionamentoDto dto = objectMapper.readValue(json, EstacionamentoDto.class);

		assertEquals(dto, new EstacionamentoDto(testEstacionamento));
	}

	@Test
	void shouldValidateCreateRequestBody() throws Exception {
		EstacionamentoForm estacionamentoForm = new EstacionamentoForm("", "",
				"", "", "", "",
				"", 0, 0);

		MvcResult response = mockMvc.perform(
				post("/estacionamentos")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(estacionamentoForm))
		).andExpect(status().isBadRequest()).andReturn();

		String json = response.getResponse().getContentAsString(StandardCharsets.UTF_8);

		List<ErroFormDto> dtos = objectMapper.readValue(json, new TypeReference<>() {
		});

		assertTrue(dtos.size() == 12);
	}

	@Test
	void shouldValidateCnpjField() throws Exception {
		EstacionamentoForm estacionamentoForm = new EstacionamentoForm("TEST01", "11111111111111",
				"AVENIDA CONSELHEIRO NÉBIAS", "1", "SANTOS", "SÃO PAULO",
				"1234567890", 1, 1);

		Mockito.when(this.estacionamentoServiceImpl.create(Mockito.any(EstacionamentoForm.class)))
				.thenThrow(new CNPJInvalidoException("número inválido"));

		MvcResult response = mockMvc.perform(
				post("/estacionamentos")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(estacionamentoForm))
		).andExpect(status().isBadRequest()).andReturn();

		String json = response.getResponse().getContentAsString(StandardCharsets.UTF_8);

		ErroDto dto = objectMapper.readValue(json, ErroDto.class);

		assertEquals(dto.getErro(), "Erro ao validar cnpj: número inválido");
	}

	@Test
	void shouldUpdateAParkingLot() throws Exception {
		Estacionamento testEstacionamento = this.testEstacionamentos.get(0);

		testEstacionamento.setNomeEstacionamento("TEST03");
		testEstacionamento.setVagasCarro(12);
		testEstacionamento.setVagasMoto(8);

		EstacionamentoUpdateForm estacionamentoForm = new EstacionamentoUpdateForm(
				"TEST03", 12, 8
		);

		Mockito.when(
				this.estacionamentoServiceImpl.update(
						Mockito.anyInt(), Mockito.any(EstacionamentoUpdateForm.class)
				)
		).thenReturn(testEstacionamento);

		MvcResult response = mockMvc.perform(
				put("/estacionamentos/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(estacionamentoForm))
		).andExpect(status().isOk()).andReturn();

		String json = response.getResponse().getContentAsString(StandardCharsets.UTF_8);

		EstacionamentoDto dto = objectMapper.readValue(json, EstacionamentoDto.class);

		assertEquals(dto, new EstacionamentoDto(testEstacionamento));
	}

	@Test
	void shouldValidateUpdateRequestBody() throws Exception {
		EstacionamentoUpdateForm estacionamentoForm = new EstacionamentoUpdateForm(
				"TEST03", 0, 0
		);

		MvcResult response = mockMvc.perform(
				put("/estacionamentos/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(estacionamentoForm))
		).andExpect(status().isBadRequest()).andReturn();

		String json = response.getResponse().getContentAsString(StandardCharsets.UTF_8);

		List<ErroFormDto> dtos = objectMapper.readValue(json, new TypeReference<>() {
		});

		assertTrue(dtos.size() == 2);
	}

	@Test
	void shouldDeleteAParkingLot() throws Exception {
		mockMvc.perform(delete("/estacionamentos/1")).andExpect(status().isNoContent());
	}

	@Test
	void shouldAddAPhoneToAParkingLot() throws Exception {
		TelefoneForm telefoneForm = new TelefoneForm("1234567890");

		mockMvc.perform(
				post("/estacionamentos/1/contato")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(telefoneForm))
		).andExpect(status().isCreated());
	}

	@Test
	void shouldValidatePhoneCreation() throws Exception {
		TelefoneForm telefoneForm = new TelefoneForm("123456789");

		mockMvc.perform(
				post("/estacionamentos/1/contato")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(telefoneForm))
		).andExpect(status().isBadRequest());

		telefoneForm.setTelefone("1234567890");

		Mockito.when(this.estacionamentoServiceImpl.addPhone(Mockito.anyInt(), Mockito.any(TelefoneForm.class)))
				.thenThrow(new OperacaoInvalidaException("telefone já vinculado ao estabelecimento"));

		mockMvc.perform(
				post("/estacionamentos/1/contato")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(telefoneForm))
		).andExpect(status().isInternalServerError());
	}

	@Test
	void shouldRemoveAParkingLotsPhone() throws Exception {
		TelefoneForm telefoneForm = new TelefoneForm("1234567890");

		mockMvc.perform(
				delete("/estacionamentos/1/contato")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(telefoneForm))
		).andExpect(status().isNoContent());
	}

	@Test
	void shouldGetAParkingLotsPhoneList() throws Exception {
		Estacionamento testEstacionamento = this.testEstacionamentos.get(0);
		Mockito.when(this.estacionamentoServiceImpl.getPhones(1)).thenReturn(testEstacionamento.getTelefones());

		MvcResult response = mockMvc.perform(get("/estacionamentos/1/contato")).andExpect(status().isOk())
				.andReturn();

		String json = response.getResponse().getContentAsString(StandardCharsets.UTF_8);

		List<String> dto = objectMapper.readValue(json, new TypeReference<>() {
		});

		assertEquals(TelefoneDto.convertListToString(testEstacionamento.getTelefones()), dto);
	}

	@Test
	void shouldValidatePhoneDeletion() throws Exception {
		TelefoneForm telefoneForm = new TelefoneForm("123456789");

		mockMvc.perform(
				delete("/estacionamentos/1/contato")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(telefoneForm))
		).andExpect(status().isBadRequest());

		telefoneForm.setTelefone("1234567890");

		Mockito.doThrow(new OperacaoInvalidaException("telefone não vinculado ao estabelecimento"))
				.when(this.estacionamentoServiceImpl).removePhone(Mockito.anyInt(), Mockito.any(TelefoneForm.class));

		mockMvc.perform(
				delete("/estacionamentos/1/contato")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(telefoneForm))
		).andExpect(status().isInternalServerError());
	}
}
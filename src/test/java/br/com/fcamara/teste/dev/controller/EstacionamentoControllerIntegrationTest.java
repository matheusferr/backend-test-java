package br.com.fcamara.teste.dev.controller;

import br.com.fcamara.teste.dev.dto.EstacionamentoDto;
import br.com.fcamara.teste.dev.dto.TelefoneDto;
import br.com.fcamara.teste.dev.entity.*;
import br.com.fcamara.teste.dev.entity.valueObject.CNPJ;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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

		String json = response.getResponse().getContentAsString();

		EstacionamentoDto dto = objectMapper.readValue(json, EstacionamentoDto.class);

		assertEquals(new EstacionamentoDto(testEstacionamento), dto);
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

		String json = response.getResponse().getContentAsString();

		EstacionamentoDto dto = objectMapper.readValue(json, EstacionamentoDto.class);

		assertEquals(dto, new EstacionamentoDto(testEstacionamento));
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

		String json = response.getResponse().getContentAsString();

		EstacionamentoDto dto = objectMapper.readValue(json, EstacionamentoDto.class);

		assertEquals(dto, new EstacionamentoDto(testEstacionamento));
	}

	@Test
	void shouldDeleteAParkingLot() throws Exception {
		mockMvc.perform(delete("/estacionamentos/1")).andExpect(status().isNoContent());
	}

	@Test
	void shouldAddAPhoneToAParkingLot() throws Exception {
		TelefoneForm telefoneForm = new TelefoneForm();
		telefoneForm.setTelefone("1234567893");

		mockMvc.perform(
				post("/estacionamentos/1/telefone")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(telefoneForm))
		).andExpect(status().isCreated());
	}

	@Test
	void shouldGetAParkingLotsPhoneList() throws Exception {
		Estacionamento testEstacionamento = this.testEstacionamentos.get(0);
		Mockito.when(this.estacionamentoServiceImpl.getPhones(1)).thenReturn(testEstacionamento.getTelefones());

		MvcResult response = mockMvc.perform(get("/estacionamentos/1/contato")).andExpect(status().isOk())
				.andReturn();

		String json = response.getResponse().getContentAsString();

		List<String> dto = objectMapper.readValue(json, new TypeReference<>() {
		});

		assertEquals(TelefoneDto.convertListToString(testEstacionamento.getTelefones()), dto);
	}

	@Test
	void shouldRemoveAParkingLotsPhone() throws Exception {
		TelefoneForm telefoneForm = new TelefoneForm();
		telefoneForm.setTelefone("1234567890");

		mockMvc.perform(
				delete("/estacionamentos/1/telefone")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(telefoneForm))
		).andExpect(status().isNoContent());
	}
}
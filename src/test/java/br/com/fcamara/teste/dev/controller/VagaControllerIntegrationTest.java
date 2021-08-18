package br.com.fcamara.teste.dev.controller;

import br.com.fcamara.teste.dev.dto.VagaDto;
import br.com.fcamara.teste.dev.entity.*;
import br.com.fcamara.teste.dev.entity.enums.VeiculoTipo;
import br.com.fcamara.teste.dev.entity.valueObject.CNPJ;
import br.com.fcamara.teste.dev.entity.valueObject.Placa;
import br.com.fcamara.teste.dev.form.vaga.VagaForm;
import br.com.fcamara.teste.dev.service.implementation.VagaServiceImpl;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VagaController.class)
class VagaControllerIntegrationTest {

	private final List<Veiculo> testVeiculos = new ArrayList<>();
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private VagaServiceImpl vagaServiceImpl;
	private Vaga testVaga;

	@BeforeEach
	void beforeEach() {
		Estado testEstado = new Estado("SÃO PAULO");
		Cidade testCidade = new Cidade("SANTOS", testEstado);

		Estacionamento testEstacionamento = new Estacionamento("TEST01", new CNPJ("45857255000103"),
				new Endereco("AVENIDA CONSELHEIRO NÉBIAS", "1", testCidade),
				new Telefone("1234567890"), 1, 1);

		Marca testMarca = new Marca("Fiat");
		Modelo testModelo = new Modelo("Palio", testMarca);
		Cor testCor = new Cor("Vermelho");
		Tipo testTipo = new Tipo(VeiculoTipo.CARRO);

		this.testVeiculos.add(new Veiculo(testModelo, testCor, new Placa("BRA1A23"), testTipo));
		this.testVeiculos.add(new Veiculo(testModelo, testCor, new Placa("BRA4A56"), testTipo));

		this.testVaga = new Vaga(this.testVeiculos.get(0), testEstacionamento);
	}

	@Test
	void shouldGetAParkingLot() throws Exception {
		Mockito.when(this.vagaServiceImpl.findById(1)).thenReturn(this.testVaga);

		MvcResult response = mockMvc.perform(get("/vagas/1")).andExpect(status().isOk()).andReturn();

		String json = response.getResponse().getContentAsString(StandardCharsets.UTF_8);

		VagaDto dto = objectMapper.readValue(json, VagaDto.class);

		assertEquals(dto, new VagaDto(this.testVaga));
	}

	@Test
	void shouldAddAVehicleToAParkingLot() throws Exception {
		VagaForm vagaForm = new VagaForm("BRA1A23", "45857255000103");

		Mockito.when(this.vagaServiceImpl.addVehicle(Mockito.any(VagaForm.class))).thenReturn(this.testVaga);

		MvcResult response = mockMvc.perform(
						post("/vagas/entrada")
								.contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(vagaForm))
				).andExpect(status().isCreated())
				.andReturn();

		String json = response.getResponse().getContentAsString(StandardCharsets.UTF_8);

		VagaDto dto = objectMapper.readValue(json, VagaDto.class);

		assertEquals(dto, new VagaDto(this.testVaga));
	}

	@Test
	void shouldRemoveAVehicleFromAParkingLot() throws Exception {
		this.testVaga.setSaida(LocalDateTime.now());

		VagaForm vagaForm = new VagaForm("BRA1A23", "45857255000103");

		Mockito.when(this.vagaServiceImpl.removeVehicle(Mockito.any(VagaForm.class))).thenReturn(this.testVaga);

		MvcResult response = mockMvc.perform(
						put("/vagas/saida")
								.contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(vagaForm))
				).andExpect(status().isOk())
				.andReturn();

		String json = response.getResponse().getContentAsString(StandardCharsets.UTF_8);

		VagaDto dto = objectMapper.readValue(json, VagaDto.class);

		assertEquals(dto, new VagaDto(this.testVaga));
	}
}
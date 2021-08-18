package br.com.fcamara.teste.dev.controller;

import br.com.fcamara.teste.dev.dto.VeiculoDto;
import br.com.fcamara.teste.dev.entity.*;
import br.com.fcamara.teste.dev.entity.enums.VeiculoTipo;
import br.com.fcamara.teste.dev.entity.valueObject.Placa;
import br.com.fcamara.teste.dev.exception.DadosInvalidosException;
import br.com.fcamara.teste.dev.form.veiculo.VeiculoForm;
import br.com.fcamara.teste.dev.form.veiculo.VeiculoUpdateForm;
import br.com.fcamara.teste.dev.service.implementation.VeiculoServiceImpl;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VeiculoController.class)
class VeiculoControllerIntegrationTest {

	private final List<Veiculo> testVeiculos = new ArrayList<>();

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private VeiculoServiceImpl veiculoServiceImpl;

	@BeforeEach
	void beforeEach() {
		Marca testMarca = new Marca("Fiat");
		Modelo testModelo = new Modelo("Palio", testMarca);
		Cor testCor = new Cor("Vermelho");
		Tipo testTipo = new Tipo(VeiculoTipo.CARRO);

		this.testVeiculos.add(new Veiculo(testModelo, testCor, new Placa("BRA1A23"), testTipo));
		this.testVeiculos.add(new Veiculo(testModelo, testCor, new Placa("BRA4A56"), testTipo));
	}

	@Test
	void shouldGetAListOfVehicles() throws Exception {
		Mockito.when(this.veiculoServiceImpl.index()).thenReturn(this.testVeiculos);
		MvcResult response = mockMvc.perform(get("/veiculos")).andExpect(status().isOk()).andReturn();

		String json = response.getResponse().getContentAsString(StandardCharsets.UTF_8);

		List<VeiculoDto> dto = objectMapper.readValue(json, new TypeReference<>() {
		});

		assertEquals(VeiculoDto.convertList(this.testVeiculos), dto);
	}

	@Test
	void shouldGetAVehicle() throws Exception {
		Veiculo testVeiculo = this.testVeiculos.get(0);
		Mockito.when(this.veiculoServiceImpl.findById(1)).thenReturn(testVeiculo);
		MvcResult response = mockMvc.perform(get("/veiculos/1")).andExpect(status().isOk()).andReturn();

		String json = response.getResponse().getContentAsString(StandardCharsets.UTF_8);

		VeiculoDto dto = objectMapper.readValue(json, VeiculoDto.class);

		assertEquals(new VeiculoDto(testVeiculo), dto);
	}

	@Test
	void shouldCreateAVehicle() throws Exception {
		VeiculoForm veiculoForm = new VeiculoForm(
				"Fiat", "Palio", "Vermelho", "BRA1A23", "carro"
		);

		Veiculo testVeiculo = this.testVeiculos.get(0);

		Mockito.when(this.veiculoServiceImpl.create(Mockito.any(VeiculoForm.class))).thenReturn(testVeiculo);

		MvcResult response = mockMvc.perform(
				post("/veiculos")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(veiculoForm))
		).andExpect(status().isCreated()).andReturn();

		String json = response.getResponse().getContentAsString(StandardCharsets.UTF_8);

		VeiculoDto dto = objectMapper.readValue(json, VeiculoDto.class);

		assertEquals(new VeiculoDto(testVeiculo), dto);
	}

	@Test
	void shouldValidateCreationRequestBody() throws Exception {
		VeiculoForm veiculoForm = new VeiculoForm(
				"", "", "", "", "carro"
		);

		mockMvc.perform(
				post("/veiculos")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(veiculoForm))
		).andExpect(status().isBadRequest());


		assertThrows(DadosInvalidosException.class, () -> veiculoForm.setTipo(""));
	}


	@Test
	void shouldUpdateAVehicle() throws Exception {
		VeiculoUpdateForm veiculoForm = new VeiculoUpdateForm("Verde");

		Veiculo testVeiculo = this.testVeiculos.get(0);

		testVeiculo.setCor(new Cor("Verde"));

		Mockito.when(this.veiculoServiceImpl.update(Mockito.anyInt(), Mockito.any(VeiculoUpdateForm.class)))
				.thenReturn(testVeiculo);

		MvcResult response = mockMvc.perform(
				put("/veiculos/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(veiculoForm))
		).andExpect(status().isOk()).andReturn();

		String json = response.getResponse().getContentAsString(StandardCharsets.UTF_8);

		VeiculoDto dto = objectMapper.readValue(json, VeiculoDto.class);

		assertEquals(new VeiculoDto(testVeiculo), dto);
	}

	@Test
	void shouldDeleteAVehicle() throws Exception {
		mockMvc.perform(delete("/veiculos/1")).andExpect(status().isNoContent());
	}
}
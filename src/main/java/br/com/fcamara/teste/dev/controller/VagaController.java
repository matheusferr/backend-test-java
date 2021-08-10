package br.com.fcamara.teste.dev.controller;

import br.com.fcamara.teste.dev.dto.VagaDto;
import br.com.fcamara.teste.dev.form.vaga.VagaForm;
import br.com.fcamara.teste.dev.service.implementation.VagaServiceImpl;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/vagas")
public class VagaController {
	private final VagaServiceImpl vagaServiceImpl;

	public VagaController(VagaServiceImpl vagaServiceImpl) {
		this.vagaServiceImpl = vagaServiceImpl;
	}

	@PutMapping("/entrada")
	@Transactional
	public VagaDto entrada(@RequestBody @Valid VagaForm vagaForm) {
		return new VagaDto(this.vagaServiceImpl.addVehicle(vagaForm));
	}

	@PutMapping("/saida")
	@Transactional
	public VagaDto saida(@RequestBody @Valid VagaForm vagaForm) {
		return new VagaDto(this.vagaServiceImpl.removeVehicle(vagaForm));
	}
}

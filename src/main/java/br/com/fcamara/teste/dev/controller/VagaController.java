package br.com.fcamara.teste.dev.controller;

import br.com.fcamara.teste.dev.dto.VagaDto;
import br.com.fcamara.teste.dev.entity.Vaga;
import br.com.fcamara.teste.dev.form.vaga.VagaForm;
import br.com.fcamara.teste.dev.service.implementation.VagaServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/vagas")
public class VagaController {
	private final VagaServiceImpl vagaServiceImpl;

	public VagaController(VagaServiceImpl vagaServiceImpl) {
		this.vagaServiceImpl = vagaServiceImpl;
	}

	@GetMapping("/{id}")
	public VagaDto findOne(@PathVariable Integer id) {
		return new VagaDto(this.vagaServiceImpl.findById(id));
	}

	@PostMapping("/entrada")
	@Transactional
	public ResponseEntity<VagaDto> enter(@RequestBody @Valid VagaForm vagaForm, UriComponentsBuilder uriBuilder) {
		Vaga vaga = this.vagaServiceImpl.addVehicle(vagaForm);

		URI uri = uriBuilder.path("/vagas/{id}").buildAndExpand(vaga.getId()).toUri();

		return ResponseEntity.created(uri).body(new VagaDto(vaga));
	}

	@PutMapping("/saida")
	@Transactional
	public VagaDto leave(@RequestBody @Valid VagaForm vagaForm) {
		return new VagaDto(this.vagaServiceImpl.removeVehicle(vagaForm));
	}
}

package br.com.fcamara.teste.dev.controller;

import br.com.fcamara.teste.dev.dto.VeiculoDto;
import br.com.fcamara.teste.dev.entity.Veiculo;
import br.com.fcamara.teste.dev.form.veiculo.VeiculoForm;
import br.com.fcamara.teste.dev.form.veiculo.VeiculoUpdateForm;
import br.com.fcamara.teste.dev.service.implementation.VeiculoServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
	private final VeiculoServiceImpl veiculoServiceImpl;

	public VeiculoController(VeiculoServiceImpl veiculoServiceImpl) {
		this.veiculoServiceImpl = veiculoServiceImpl;
	}

	@GetMapping
	public List<VeiculoDto> index() {
		return VeiculoDto.convertList(this.veiculoServiceImpl.index());
	}

	@GetMapping("/{id}")
	public VeiculoDto findOne(@PathVariable Integer id) {
		return new VeiculoDto(this.veiculoServiceImpl.findById(id));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<VeiculoDto> create(@RequestBody @Valid VeiculoForm veiculoForm,
	                                         UriComponentsBuilder uriBuilder) {
		Veiculo veiculo = this.veiculoServiceImpl.create(veiculoForm);

		URI uri = uriBuilder.path("/veiculos/{id}").buildAndExpand(veiculo.getId()).toUri();

		return ResponseEntity.created(uri).body(new VeiculoDto(veiculo));
	}

	@PutMapping("/{id}")
	@Transactional
	public VeiculoDto update(@PathVariable Integer id, @RequestBody @Valid VeiculoUpdateForm veiculoUpdateForm) {
		return new VeiculoDto(this.veiculoServiceImpl.update(id, veiculoUpdateForm));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> destroy(@PathVariable Integer id) {
		this.veiculoServiceImpl.destroy(id);
		return ResponseEntity.noContent().build();
	}
}

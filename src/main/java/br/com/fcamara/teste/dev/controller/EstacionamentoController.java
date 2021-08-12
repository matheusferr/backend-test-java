package br.com.fcamara.teste.dev.controller;

import br.com.fcamara.teste.dev.dto.EstacionamentoDto;
import br.com.fcamara.teste.dev.dto.TelefoneDto;
import br.com.fcamara.teste.dev.form.contato.TelefoneForm;
import br.com.fcamara.teste.dev.form.estacionamento.EstacionamentoForm;
import br.com.fcamara.teste.dev.form.estacionamento.EstacionamentoUpdateForm;
import br.com.fcamara.teste.dev.service.implementation.EstacionamentoServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/estacionamentos")
public class EstacionamentoController {
	private final EstacionamentoServiceImpl estabelecimentoServiceImpl;

	public EstacionamentoController(EstacionamentoServiceImpl estabelecimentoServiceImpl) {
		this.estabelecimentoServiceImpl = estabelecimentoServiceImpl;
	}

	@GetMapping
	public List<EstacionamentoDto> index() {
		return EstacionamentoDto.convertList(this.estabelecimentoServiceImpl.index());
	}

	@GetMapping("/{id}")
	public EstacionamentoDto findOne(@PathVariable Integer id) {
		return new EstacionamentoDto(this.estabelecimentoServiceImpl.findById(id));
	}

	@GetMapping("/{id}/contato")
	public List<String> findContacts(@PathVariable Integer id) {
		return TelefoneDto.convertListToString(this.estabelecimentoServiceImpl.getPhones(id));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<EstacionamentoDto> create(@RequestBody @Valid EstacionamentoForm estacionamentoForm,
	                                                UriComponentsBuilder uriBuilder) {

		EstacionamentoDto estabelecimento = new EstacionamentoDto(
				this.estabelecimentoServiceImpl.create(estacionamentoForm)
		);

		URI uri = uriBuilder.path("/estabelecimentos/{id}").buildAndExpand(estabelecimento.getId()).toUri();

		return ResponseEntity.created(uri).body(estabelecimento);
	}

	@PostMapping("/{id}/telefone")
	@Transactional
	public ResponseEntity<?> addPhone(@PathVariable Integer id,
	                                  @RequestBody @Valid TelefoneForm estabelecimentoForm,
	                                  UriComponentsBuilder uriBuilder) {

		this.estabelecimentoServiceImpl.addPhone(id, estabelecimentoForm);

		URI uri = uriBuilder.path("/estabelecimentos/{id}/contato").buildAndExpand(id)
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	@Transactional
	public EstacionamentoDto update(@PathVariable Integer id,
	                                @RequestBody @Valid EstacionamentoUpdateForm estabelecimentoForm) {
		return new EstacionamentoDto(this.estabelecimentoServiceImpl.update(id, estabelecimentoForm));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> destroy(@PathVariable Integer id) {
		this.estabelecimentoServiceImpl.destroy(id);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}/telefone")
	@Transactional
	public ResponseEntity<?> removePhone(@PathVariable Integer id,
	                                     @RequestBody @Valid TelefoneForm estabelecimentoForm) {
		this.estabelecimentoServiceImpl.removePhone(id, estabelecimentoForm);

		return ResponseEntity.noContent().build();
	}

}

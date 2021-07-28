package br.com.fcamara.teste.dev.controller;

import br.com.fcamara.teste.dev.dto.EstabelecimentoDto;
import br.com.fcamara.teste.dev.dto.TelefoneDto;
import br.com.fcamara.teste.dev.form.estabelecimento.EstabelecimentoForm;
import br.com.fcamara.teste.dev.form.estabelecimento.EstabelecimentoTelefoneForm;
import br.com.fcamara.teste.dev.form.estabelecimento.EstabelecimentoUpdateForm;
import br.com.fcamara.teste.dev.service.implementation.EstabelecimentoServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/estabelecimentos")
public class EstabelecimentoController {
    private final EstabelecimentoServiceImpl estabelecimentoServiceImpl;

    public EstabelecimentoController(EstabelecimentoServiceImpl estabelecimentoServiceImpl) {
        this.estabelecimentoServiceImpl = estabelecimentoServiceImpl;
    }

    @GetMapping
    public List<EstabelecimentoDto> index() {
        return EstabelecimentoDto.convertList(this.estabelecimentoServiceImpl.index());
    }

    @GetMapping("/{id}")
    public EstabelecimentoDto findOne(@PathVariable Integer id) {
        return new EstabelecimentoDto(this.estabelecimentoServiceImpl.findById(id));
    }

    @GetMapping("/{id}/contato")
    public List<String> findContatcs(@PathVariable Integer id) {
        return TelefoneDto.convertListToString(this.estabelecimentoServiceImpl.getPhones(id));
    }

    @PostMapping
    public ResponseEntity<EstabelecimentoDto> create(@RequestBody @Valid EstabelecimentoForm estabelecimentoForm,
                                                     UriComponentsBuilder uriBuilder) {

        EstabelecimentoDto estabelecimento = new EstabelecimentoDto(
                this.estabelecimentoServiceImpl.create(estabelecimentoForm)
        );

        URI uri = uriBuilder.path("/estabelecimentos/{id}").buildAndExpand(estabelecimento.getId()).toUri();

        return ResponseEntity.created(uri).body(estabelecimento);
    }

    @PostMapping("/{id}/telefone")
    public ResponseEntity<?> addPhone(@PathVariable Integer id,
                                      @RequestBody @Valid EstabelecimentoTelefoneForm estabelecimentoForm,
                                      UriComponentsBuilder uriBuilder) {

        this.estabelecimentoServiceImpl.addPhone(id, estabelecimentoForm);

        URI uri = uriBuilder.path("/estabelecimentos/{id}/contato").buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public EstabelecimentoDto update(@PathVariable Integer id, @RequestBody EstabelecimentoUpdateForm estabelecimentoForm) {
        return new EstabelecimentoDto(this.estabelecimentoServiceImpl.update(id, estabelecimentoForm));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable Integer id) {
        this.estabelecimentoServiceImpl.destroy(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/telefone")
    public ResponseEntity<?> removePhone(@PathVariable Integer id,
                                         @RequestBody @Valid EstabelecimentoTelefoneForm estabelecimentoForm) {
        this.estabelecimentoServiceImpl.removePhone(id, estabelecimentoForm);

        return ResponseEntity.noContent().build();
    }

}

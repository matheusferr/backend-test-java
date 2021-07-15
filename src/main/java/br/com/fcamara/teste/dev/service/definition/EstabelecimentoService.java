package br.com.fcamara.teste.dev.service.definition;

import br.com.fcamara.teste.dev.dto.EstabelecimentoDto;
import br.com.fcamara.teste.dev.form.estabelecimento.EstabelecimentoForm;
import br.com.fcamara.teste.dev.form.estabelecimento.EstabelecimentoUpdateForm;

import java.util.List;

public interface EstabelecimentoService {
    List<EstabelecimentoDto> index();
    EstabelecimentoDto findById(Integer id);
    EstabelecimentoDto create(EstabelecimentoForm estabelecimentoForm);
    EstabelecimentoDto update(Integer id, EstabelecimentoUpdateForm estabelecimentoUpdateForm);
    void destroy(Integer id);
}

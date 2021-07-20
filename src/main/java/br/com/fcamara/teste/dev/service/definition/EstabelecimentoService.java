package br.com.fcamara.teste.dev.service.definition;

import br.com.fcamara.teste.dev.dto.EstabelecimentoDto;
import br.com.fcamara.teste.dev.entity.Estabelecimento;
import br.com.fcamara.teste.dev.form.estabelecimento.EstabelecimentoForm;
import br.com.fcamara.teste.dev.form.estabelecimento.EstabelecimentoTelefoneForm;
import br.com.fcamara.teste.dev.form.estabelecimento.EstabelecimentoUpdateForm;

import java.util.List;
import java.util.Optional;

public interface EstabelecimentoService {
    List<Estabelecimento> index();
    Estabelecimento findById(Integer id);
    Estabelecimento create(EstabelecimentoForm estabelecimentoForm);
    Estabelecimento update(Integer id, EstabelecimentoUpdateForm estabelecimentoUpdateForm);
    Estabelecimento addPhone(Integer id, EstabelecimentoTelefoneForm estabelecimentoTelefoneForm);
    void removePhone(Integer id, EstabelecimentoTelefoneForm estabelecimentoTelefoneForm);
    void destroy(Integer id);
}

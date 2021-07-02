package br.com.fcamara.teste.dev.service.definition;

import br.com.fcamara.teste.dev.dto.VeiculoDto;
import br.com.fcamara.teste.dev.form.VeiculoForm;
import br.com.fcamara.teste.dev.form.VeiculoUpdateForm;

import java.util.List;

public interface VeiculoService {
    List<VeiculoDto> index();
    VeiculoDto findById(Integer id);
    VeiculoDto create(VeiculoForm veiculoForm);
    VeiculoDto update(Integer id, VeiculoUpdateForm veiculoUpdateForm);
    void destroy(Integer id);
}

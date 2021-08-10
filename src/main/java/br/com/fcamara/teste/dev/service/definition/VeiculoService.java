package br.com.fcamara.teste.dev.service.definition;

import br.com.fcamara.teste.dev.entity.Veiculo;
import br.com.fcamara.teste.dev.form.veiculo.VeiculoForm;
import br.com.fcamara.teste.dev.form.veiculo.VeiculoUpdateForm;

import java.util.List;

public interface VeiculoService {
	List<Veiculo> index();

	Veiculo findById(Integer id);

	Veiculo findByPlaca(String placa);

	Veiculo create(VeiculoForm veiculoForm);

	Veiculo update(Integer id, VeiculoUpdateForm veiculoUpdateForm);

	void destroy(Integer id);
}

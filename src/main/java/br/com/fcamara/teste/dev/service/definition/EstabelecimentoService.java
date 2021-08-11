package br.com.fcamara.teste.dev.service.definition;

import br.com.fcamara.teste.dev.entity.Estacionamento;
import br.com.fcamara.teste.dev.entity.Telefone;
import br.com.fcamara.teste.dev.form.contato.TelefoneForm;
import br.com.fcamara.teste.dev.form.estacionamento.EstacionamentoForm;
import br.com.fcamara.teste.dev.form.estacionamento.EstacionamentoUpdateForm;

import java.util.List;

public interface EstabelecimentoService {
	List<Estacionamento> index();

	Estacionamento findById(Integer id);

	Estacionamento findByCnpj(String cnpj);

	Estacionamento create(EstacionamentoForm estacionamentoForm);

	Estacionamento update(Integer id, EstacionamentoUpdateForm estacionamentoUpdateForm);

	void updateVagas(Estacionamento estacionamento);

	List<Telefone> getPhones(Integer id);

	Estacionamento addPhone(Integer id, TelefoneForm telefoneForm);

	void removePhone(Integer id, TelefoneForm telefoneForm);

	void destroy(Integer id);
}

package br.com.fcamara.teste.dev.service.definition;

import br.com.fcamara.teste.dev.entity.Vaga;
import br.com.fcamara.teste.dev.form.vaga.VagaForm;

import java.util.List;

public interface VagaService {
	Vaga findById(Integer id);
	Vaga addVehicle(VagaForm vagaForm);
	Vaga removeVehicle(VagaForm vagaForm);
}

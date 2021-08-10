package br.com.fcamara.teste.dev.service.definition;

import br.com.fcamara.teste.dev.entity.Marca;
import br.com.fcamara.teste.dev.entity.Modelo;

import java.util.Optional;

public interface ModeloService {
	Optional<Modelo> findByNomeModelo(String nomeModelo);

	Modelo create(String nomeModelo, Marca marca);
}

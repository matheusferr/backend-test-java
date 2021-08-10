package br.com.fcamara.teste.dev.service.definition;

import br.com.fcamara.teste.dev.entity.Cidade;
import br.com.fcamara.teste.dev.entity.Estado;

import java.util.Optional;

public interface CidadeService {
	Optional<Cidade> findByNomeCidade(String nomeCidade);

	Cidade create(String nomeCidade, Estado estado);
}

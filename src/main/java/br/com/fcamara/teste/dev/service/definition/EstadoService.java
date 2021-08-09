package br.com.fcamara.teste.dev.service.definition;

import br.com.fcamara.teste.dev.entity.Estado;

public interface EstadoService {
	Estado findByNomeOrCreate(String nomeEstado);
}

package br.com.fcamara.teste.dev.service.definition;

import br.com.fcamara.teste.dev.entity.Tipo;
import br.com.fcamara.teste.dev.entity.enums.VeiculoTipo;

public interface TipoService {
	Tipo findOneOrCreate(VeiculoTipo veiculoTipo);
}

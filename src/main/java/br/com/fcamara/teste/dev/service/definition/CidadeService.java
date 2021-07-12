package br.com.fcamara.teste.dev.service.definition;

import br.com.fcamara.teste.dev.entity.Cidade;
import br.com.fcamara.teste.dev.entity.Estado;

public interface CidadeService {
    Cidade findByNomeCidadeOrCreate(String nomeCidade, Estado estado);
}

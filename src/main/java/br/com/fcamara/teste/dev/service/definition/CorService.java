package br.com.fcamara.teste.dev.service.definition;

import br.com.fcamara.teste.dev.entity.Cor;

public interface CorService {
    Cor findByNomeOrCreate(String nomeCor);
}

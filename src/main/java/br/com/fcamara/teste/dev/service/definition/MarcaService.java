package br.com.fcamara.teste.dev.service.definition;

import br.com.fcamara.teste.dev.entity.Marca;

public interface MarcaService {
    Marca findByNomeOrCreate(String nomeMarca);
}

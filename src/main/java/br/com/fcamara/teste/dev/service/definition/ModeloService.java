package br.com.fcamara.teste.dev.service.definition;

import br.com.fcamara.teste.dev.entity.Marca;
import br.com.fcamara.teste.dev.entity.Modelo;

public interface ModeloService {
    Modelo findByNomeModeloOrCreate(String nomeModelo, Marca marca);
}

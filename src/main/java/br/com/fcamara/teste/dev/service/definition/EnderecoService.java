package br.com.fcamara.teste.dev.service.definition;

import br.com.fcamara.teste.dev.entity.Endereco;

public interface EnderecoService {
    Endereco findOrCreate(String logradouro, String numero, String nomeCidade, String nomeEstado);
}

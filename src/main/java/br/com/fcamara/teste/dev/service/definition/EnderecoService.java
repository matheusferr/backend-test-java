package br.com.fcamara.teste.dev.service.definition;

import br.com.fcamara.teste.dev.entity.Endereco;

public interface EnderecoService {
    Endereco findByLogradouroAndNumero(String logradouro, Integer numero);
}

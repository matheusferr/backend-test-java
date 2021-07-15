package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Cidade;
import br.com.fcamara.teste.dev.entity.Endereco;
import br.com.fcamara.teste.dev.service.definition.EnderecoService;

import java.util.Optional;

public class EnderecoServiceImpl implements EnderecoService {

    @Override
    public Optional<Endereco> findByLogradouroAndNumero(String logradouro, Integer numero) {
        return null;
    }

    @Override
    public Endereco create(String logradouro, Integer numero, Cidade cidade) {
        return null;
    }
}

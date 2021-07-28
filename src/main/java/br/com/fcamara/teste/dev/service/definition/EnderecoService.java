package br.com.fcamara.teste.dev.service.definition;

import br.com.fcamara.teste.dev.entity.Cidade;
import br.com.fcamara.teste.dev.entity.Endereco;

import java.util.Optional;

public interface EnderecoService {
    //Optional<Endereco> findByLogradouroAndNumero(String logradouro, String numero);
    Endereco findOrCreate(String logradouro, String numero, String nomeCidade, String nomeEstado);
}

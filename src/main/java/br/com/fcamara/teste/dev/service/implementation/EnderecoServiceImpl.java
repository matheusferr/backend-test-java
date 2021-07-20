package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Cidade;
import br.com.fcamara.teste.dev.entity.Endereco;
import br.com.fcamara.teste.dev.repository.EnderecoRepository;
import br.com.fcamara.teste.dev.service.definition.EnderecoService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoServiceImpl implements EnderecoService {
    private EnderecoRepository enderecoRepository;

    public EnderecoServiceImpl(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public Optional<Endereco> findByLogradouroAndNumero(String logradouro, String numero) {
        return this.enderecoRepository.findByLogradouroAndNumero(logradouro, numero);
    }

    @Override
    public Endereco create(String logradouro, String numero, Cidade cidade) {
        return this.enderecoRepository.save(new Endereco(logradouro, numero, cidade));
    }
}

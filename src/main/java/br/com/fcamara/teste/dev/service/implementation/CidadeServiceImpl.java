package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Cidade;
import br.com.fcamara.teste.dev.entity.Estado;
import br.com.fcamara.teste.dev.repository.CidadeRepository;
import br.com.fcamara.teste.dev.service.definition.CidadeService;

import java.util.Optional;

public class CidadeServiceImpl implements CidadeService {
    private CidadeRepository cidadeRepository;

    public CidadeServiceImpl(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    @Override
    public Optional<Cidade> findByNomeCidade(String nomeCidade) {
        return null;
    }

    @Override
    public Cidade create(String nomeCidade, Estado estado) {
        return null;
    }
}

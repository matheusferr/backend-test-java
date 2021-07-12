package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Cidade;
import br.com.fcamara.teste.dev.entity.Estado;
import br.com.fcamara.teste.dev.repository.CidadeRepository;
import br.com.fcamara.teste.dev.service.definition.CidadeService;

public class CidadeServiceImpl implements CidadeService {
    private CidadeRepository cidadeRepository;

    public CidadeServiceImpl(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    @Override
    public Cidade findByNomeCidadeOrCreate(String nomeCidade, Estado estado) {
        return null;
    }
}

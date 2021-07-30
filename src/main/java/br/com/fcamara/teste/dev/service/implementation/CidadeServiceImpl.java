package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Cidade;
import br.com.fcamara.teste.dev.entity.Estado;
import br.com.fcamara.teste.dev.repository.CidadeRepository;
import br.com.fcamara.teste.dev.service.definition.CidadeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CidadeServiceImpl implements CidadeService {
    private final CidadeRepository cidadeRepository;

    public CidadeServiceImpl(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    @Override
    public Optional<Cidade> findByNomeCidade(String nomeCidade) {
        return this.cidadeRepository.findByNomeCidade(nomeCidade);
    }

    @Override
    public Cidade create(String nomeCidade, Estado estado) {
        return this.cidadeRepository.save(new Cidade(nomeCidade, estado));
    }
}

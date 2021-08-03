package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Cor;
import br.com.fcamara.teste.dev.repository.CorRepository;
import br.com.fcamara.teste.dev.service.definition.CorService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CorServiceImpl implements CorService {
    private final CorRepository corRepository;

    public CorServiceImpl(CorRepository corRepository) {
        this.corRepository = corRepository;
    }

    @Override
    public Cor findByNomeOrCreate(String nomeCor) {
        Optional<Cor> cor = this.corRepository.findByNomeCor(nomeCor.toUpperCase());

        if (cor.isEmpty()) return this.corRepository.save(new Cor(nomeCor.toUpperCase()));

        return cor.get();
    }
}

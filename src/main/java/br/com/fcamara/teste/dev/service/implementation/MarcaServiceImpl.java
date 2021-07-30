package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Marca;
import br.com.fcamara.teste.dev.repository.MarcaRepository;
import br.com.fcamara.teste.dev.service.definition.MarcaService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MarcaServiceImpl implements MarcaService {
    private final MarcaRepository marcaRepository;

    public MarcaServiceImpl(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    @Override
    public Marca findByNomeOrCreate(String nomeMarca) {
        Optional<Marca> marca = this.marcaRepository.findByNomeMarca(nomeMarca);

        if(marca.isEmpty()) return this.marcaRepository.save(new Marca(nomeMarca));

        return marca.get();
    }
}

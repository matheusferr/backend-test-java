package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Marca;
import br.com.fcamara.teste.dev.entity.Modelo;
import br.com.fcamara.teste.dev.repository.ModeloRepository;
import br.com.fcamara.teste.dev.service.definition.ModeloService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModeloServiceImpl implements ModeloService {
    private ModeloRepository modeloRepository;

    public ModeloServiceImpl(ModeloRepository modeloRepository) {
        this.modeloRepository = modeloRepository;
    }

    @Override
    public Optional<Modelo> findByNomeModelo(String nomeModelo) {
        return this.modeloRepository.findByNomeModelo(nomeModelo);
    }

    @Override
    public Modelo create(String nomeModelo, Marca marca) {
        return this.modeloRepository.save(new Modelo(nomeModelo, marca));
    }
}

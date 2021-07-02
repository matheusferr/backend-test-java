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
    public Modelo findByNomeAndMarcaOrCreate(String nomeModelo, Marca marca) {
        Optional<Modelo> modelo = this.modeloRepository.findByNomeModelo(nomeModelo);

        if(modelo.isEmpty()) return this.modeloRepository.save(new Modelo(nomeModelo, marca));

        return modelo.get();
    }
}

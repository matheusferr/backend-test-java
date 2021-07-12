package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Estado;
import br.com.fcamara.teste.dev.repository.EstadoRepository;
import br.com.fcamara.teste.dev.service.definition.EstadoService;

public class EstadoServiceImpl implements EstadoService {
    private EstadoRepository estadoRepository;

    public EstadoServiceImpl(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @Override
    public Estado findByNomeOrCreate(String nomeEstado) {
        return null;
    }
}

package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Estado;
import br.com.fcamara.teste.dev.repository.EstadoRepository;
import br.com.fcamara.teste.dev.service.definition.EstadoService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstadoServiceImpl implements EstadoService {
	private final EstadoRepository estadoRepository;

	public EstadoServiceImpl(EstadoRepository estadoRepository) {
		this.estadoRepository = estadoRepository;
	}

	@Override
	public Estado findByNomeOrCreate(String nomeEstado) {
		Optional<Estado> estado = this.estadoRepository.findByNomeEstado(nomeEstado);

		if(estado.isEmpty()) return this.estadoRepository.save(new Estado(nomeEstado));

		return estado.get();
	}
}

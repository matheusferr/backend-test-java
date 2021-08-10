package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Tipo;
import br.com.fcamara.teste.dev.entity.enums.VeiculoTipo;
import br.com.fcamara.teste.dev.repository.TipoRepository;
import br.com.fcamara.teste.dev.service.definition.TipoService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoServiceImpl implements TipoService {
	private final TipoRepository tipoRepository;

	public TipoServiceImpl(TipoRepository tipoRepository) {
		this.tipoRepository = tipoRepository;
	}

	@Override
	public Tipo findOneOrCreate(VeiculoTipo veiculoTipo) {
		Optional<Tipo> tipo = this.tipoRepository.findByTipo(veiculoTipo);

		if(tipo.isEmpty()) return this.tipoRepository.save(new Tipo(veiculoTipo));

		return tipo.get();
	}
}

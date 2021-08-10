package br.com.fcamara.teste.dev.repository;

import br.com.fcamara.teste.dev.entity.Tipo;
import br.com.fcamara.teste.dev.entity.enums.VeiculoTipo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TipoRepository extends CrudRepository<Tipo, Integer> {
	Optional<Tipo> findByTipo(VeiculoTipo tipo);
}

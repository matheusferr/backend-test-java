package br.com.fcamara.teste.dev.repository;

import br.com.fcamara.teste.dev.entity.Modelo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ModeloRepository extends CrudRepository<Modelo, Integer> {
	Optional<Modelo> findByNomeModelo(String nomeModelo);
}

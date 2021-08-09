package br.com.fcamara.teste.dev.repository;

import br.com.fcamara.teste.dev.entity.Cidade;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CidadeRepository extends CrudRepository<Cidade, Integer> {
	Optional<Cidade> findByNomeCidade(String nomeCidade);
}

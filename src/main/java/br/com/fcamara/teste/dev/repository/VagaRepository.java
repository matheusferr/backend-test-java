package br.com.fcamara.teste.dev.repository;

import br.com.fcamara.teste.dev.entity.Vaga;
import br.com.fcamara.teste.dev.entity.Veiculo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VagaRepository extends CrudRepository<Vaga, Integer> {
	Optional<Vaga> findByVeiculoAndSaidaNull(Veiculo veiculo);
}

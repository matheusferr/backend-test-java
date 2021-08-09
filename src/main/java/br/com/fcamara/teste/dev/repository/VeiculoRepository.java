package br.com.fcamara.teste.dev.repository;

import br.com.fcamara.teste.dev.entity.Veiculo;
import br.com.fcamara.teste.dev.entity.valueObject.Placa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends CrudRepository<Veiculo, Integer> {
	List<Veiculo> findAll();

	Optional<Veiculo> findByPlaca(Placa placa);
}

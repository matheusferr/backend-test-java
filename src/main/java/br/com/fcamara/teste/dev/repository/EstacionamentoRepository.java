package br.com.fcamara.teste.dev.repository;

import br.com.fcamara.teste.dev.entity.Estacionamento;
import br.com.fcamara.teste.dev.entity.valueObject.CNPJ;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EstacionamentoRepository extends CrudRepository<Estacionamento, Integer> {
	List<Estacionamento> findAll();

	Optional<Estacionamento> findByCnpj(CNPJ cnpj);
}

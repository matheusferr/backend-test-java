package br.com.fcamara.teste.dev.repository;

import br.com.fcamara.teste.dev.entity.Cor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CorRepository extends CrudRepository<Cor, Integer> {
    Optional<Cor> findByNomeCor(String nomeCor);
}

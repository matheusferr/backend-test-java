package br.com.fcamara.teste.dev.repository;

import br.com.fcamara.teste.dev.entity.Marca;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MarcaRepository extends CrudRepository<Marca, Integer> {
    Optional<Marca> findByNomeMarca(String nomeMarca);
}

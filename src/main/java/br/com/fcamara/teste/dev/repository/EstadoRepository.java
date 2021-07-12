package br.com.fcamara.teste.dev.repository;

import br.com.fcamara.teste.dev.entity.Estado;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EstadoRepository extends CrudRepository<Estado, Integer> {
    Optional<Estado> findByNomeEstado(String nomeEstado);
}

package br.com.fcamara.teste.dev.repository;

import br.com.fcamara.teste.dev.entity.Vagas;
import org.springframework.data.repository.CrudRepository;

public interface VagaRepository extends CrudRepository<Vagas, Integer> {
}

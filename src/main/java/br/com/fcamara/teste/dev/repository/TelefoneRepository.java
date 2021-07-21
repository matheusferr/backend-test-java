package br.com.fcamara.teste.dev.repository;

import br.com.fcamara.teste.dev.entity.Telefone;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TelefoneRepository extends CrudRepository<Telefone, Integer> {
    Optional<Telefone> findByNumeroTelefone(String numeroTelefone);
}

package br.com.fcamara.teste.dev.repository;

import br.com.fcamara.teste.dev.entity.Estabelecimento;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EstabelecimentoRepository extends CrudRepository<Estabelecimento, Integer> {
    List<Estabelecimento> findAll();
    Optional<Estabelecimento> findByCnpj(String cnpj);
}

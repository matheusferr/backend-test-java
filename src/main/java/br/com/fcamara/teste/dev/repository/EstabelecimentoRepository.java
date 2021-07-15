package br.com.fcamara.teste.dev.repository;

import br.com.fcamara.teste.dev.entity.Estabelecimento;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EstabelecimentoRepository extends CrudRepository<Estabelecimento, Integer> {
    List<Estabelecimento> findAll();
}

package br.com.fcamara.teste.dev.repository;

import br.com.fcamara.teste.dev.entity.Endereco;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface EnderecoRepository extends CrudRepository<Endereco, Integer> {
	Optional<Endereco> findByLogradouroAndNumero(String logradouro, String numero);
}

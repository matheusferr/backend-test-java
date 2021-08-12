package br.com.fcamara.teste.dev.entity;

import br.com.fcamara.teste.dev.exception.EnderecoInvalidoException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Enderecos")
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String logradouro;

	private String numero;

	@ManyToOne(cascade = CascadeType.ALL)
	private Cidade cidade;

	private void validate(String numero) {
		if(!numero.equalsIgnoreCase("S/N")) {
			Integer num = Integer.parseInt(numero);

			if(num < 0) throw new EnderecoInvalidoException();
		}
	}

	public void setNumero(String numero) {
		this.validate(numero);

		this.numero = numero;
	}

	public Endereco(String logradouro, String numero, Cidade cidade) {
		this.validate(numero);

		this.logradouro = logradouro;
		this.numero = numero;
		this.cidade = cidade;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Endereco endereco = (Endereco) o;

		return Objects.equals(logradouro, endereco.logradouro) && Objects.equals(numero, endereco.numero)
				&& Objects.equals(cidade, endereco.cidade);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());

		return result;
	}

	@PrePersist
	@PreUpdate
	private void prePersist() {
		this.logradouro = this.logradouro.toUpperCase();
		this.numero = this.numero.toUpperCase();
	}
}

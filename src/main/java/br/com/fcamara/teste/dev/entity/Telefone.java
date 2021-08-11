package br.com.fcamara.teste.dev.entity;

import br.com.fcamara.teste.dev.exception.TelefoneInvalidoException;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Entity
@Table(name = "Telefones")
public class Telefone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true)
	private String numero;

	public Telefone(String numero) {
		this.validate(numero);

		this.numero = numero;
	}

	private void validate(String telefone) {
		if(!telefone.matches("^\\d{11}|\\d{10}$"))
			throw new TelefoneInvalidoException();
	}

	public String getTelefoneValue() {
		return this.numero;
	}

	public void setTelefone(String numero) {
		this.validate(numero);

		this.numero = numero;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Telefone that = (Telefone) o;

		return Objects.equals(numero, that.numero);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());

		return result;
	}
}

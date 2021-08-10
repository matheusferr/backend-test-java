package br.com.fcamara.teste.dev.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Cidades")
public class Cidade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nomeCidade;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Estado estado;

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Cidade cidade = (Cidade) o;

		return Objects.equals(id, cidade.id) && Objects.equals(nomeCidade, cidade.nomeCidade)
				&& Objects.equals(estado, cidade.estado);
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
		this.nomeCidade = this.nomeCidade.toUpperCase();
	}

	public Cidade(String nomeCidade, Estado estado) {
		this.nomeCidade = nomeCidade;
		this.estado = estado;
	}
}

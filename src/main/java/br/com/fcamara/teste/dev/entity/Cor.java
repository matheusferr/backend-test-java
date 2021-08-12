package br.com.fcamara.teste.dev.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Cores")
public class Cor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NonNull
	private String nomeCor;

	@PrePersist
	private void prePersist() {
		this.nomeCor = this.nomeCor.toUpperCase();
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Cor that = (Cor) o;

		return Objects.equals(nomeCor, that.nomeCor);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());

		return result;
	}

	public Cor(String nomeCor) {
		this.nomeCor = nomeCor;
	}
}

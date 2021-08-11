package br.com.fcamara.teste.dev.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Modelos")
public class Modelo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nomeModelo;

	@ManyToOne
	private Marca marca;

	public Modelo(String nomeModelo, Marca marca) {
		this.nomeModelo = nomeModelo;
		this.marca = marca;
	}

	@PrePersist
	private void prePersist() {
		this.nomeModelo = this.nomeModelo.toUpperCase();
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Modelo that = (Modelo) o;

		return Objects.equals(nomeModelo, that.nomeModelo) && Objects.equals(marca, that.marca);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());

		return result;
	}
}
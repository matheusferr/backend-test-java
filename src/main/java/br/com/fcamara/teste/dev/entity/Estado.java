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
@Table(name = "Estados")
public class Estado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nomeEstado;

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Estado that = (Estado) o;

		return Objects.equals(nomeEstado, that.nomeEstado);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());

		return result;
	}

	@PrePersist
	private void prePersist() {
		this.nomeEstado = this.nomeEstado.toUpperCase();
	}

	public Estado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}
}

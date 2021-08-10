package br.com.fcamara.teste.dev.entity;

import br.com.fcamara.teste.dev.entity.enums.VeiculoTipo;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Setter
@Entity
@Table(name = "Tipos")
public class Tipo {
	@Id
	@GeneratedValue
	private Integer id;

	@Enumerated(EnumType.STRING)
	private VeiculoTipo tipo;

	public Tipo(VeiculoTipo tipo) {
		this.tipo = tipo;
	}

	public String getTipoValue() {
		return tipo.toString();
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Tipo that = (Tipo) o;

		return Objects.equals(tipo, that.tipo);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());

		return result;
	}
}

package br.com.fcamara.teste.dev.entity;

import br.com.fcamara.teste.dev.entity.converter.PlacaConverter;
import br.com.fcamara.teste.dev.entity.enums.VeiculoTipo;
import br.com.fcamara.teste.dev.entity.valueObject.Placa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Veiculos")
public class Veiculo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Modelo modelo;

	@ManyToOne
	private Cor cor;

	@Convert(converter = PlacaConverter.class)
	@Column(unique = true)
	private Placa placa;

	@Enumerated(EnumType.STRING)
	private VeiculoTipo tipo;

	public Veiculo(Modelo modelo, Cor cor, Placa placa, VeiculoTipo tipo) {
		this.modelo = modelo;
		this.cor = cor;
		this.placa = placa;
		this.tipo = tipo;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Veiculo that = (Veiculo) o;

		return Objects.equals(modelo, that.modelo) && Objects.equals(cor, that.cor) &&
				Objects.equals(placa, that.placa) && tipo == that.tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());

		return result;
	}
}

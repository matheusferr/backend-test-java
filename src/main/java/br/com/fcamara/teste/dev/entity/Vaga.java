package br.com.fcamara.teste.dev.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Vagas")
public class Vaga {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Estacionamento estacionamento;

	@OneToOne
	private Veiculo veiculo;

	@OneToOne(cascade = CascadeType.ALL)
	private Tipo tipo;

	private LocalDateTime entrada;

	private LocalDateTime saida;

	public Vaga(Veiculo veiculo, Estacionamento estacionamento) {
		this.estacionamento = estacionamento;
		this.veiculo = veiculo;
		this.tipo = veiculo.getTipo();
		this.entrada = LocalDateTime.now();
		this.saida = null;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Vaga that = (Vaga) o;

		return Objects.equals(veiculo, that.veiculo);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());

		return result;
	}
}

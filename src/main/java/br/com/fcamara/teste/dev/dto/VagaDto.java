package br.com.fcamara.teste.dev.dto;

import br.com.fcamara.teste.dev.entity.Vaga;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class VagaDto {
	private String estacionamento;

	private String placa;

	private LocalDateTime entrada;

	private LocalDateTime saida;

	public VagaDto(Vaga vaga) {
		this.estacionamento = vaga.getEstacionamento().getNomeEstacionamento();
		this.placa = vaga.getVeiculo().getPlaca().getPlacaValue();
		this.entrada = vaga.getEntrada();
		this.saida = vaga.getSaida();
	}
}

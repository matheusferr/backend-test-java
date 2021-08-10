package br.com.fcamara.teste.dev.dto;

import br.com.fcamara.teste.dev.entity.Vaga;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class VagaDto {
	private final String placa;

	private final LocalDateTime entrada;

	private final LocalDateTime saida;

	public VagaDto(Vaga vaga) {
		this.placa = vaga.getVeiculo().getPlaca().getPlacaValue();
		this.entrada = vaga.getEntrada();
		this.saida = vaga.getSaida();
	}
}

package br.com.fcamara.teste.dev.dto;

import br.com.fcamara.teste.dev.entity.Estacionamento;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EstabelecimentoDto {
	private final Integer id;

	private final String nome;

	private final String cnpj;

	public static List<EstabelecimentoDto> convertList(List<Estacionamento> estacionamentos) {
		return estacionamentos.stream().map(EstabelecimentoDto::new).collect(Collectors.toList());
	}

	public EstabelecimentoDto(Estacionamento estacionamento) {
		this.id = estacionamento.getId();
		this.nome = estacionamento.getNome();
		this.cnpj = estacionamento.getCnpj().getCnpjValue();
	}
}

package br.com.fcamara.teste.dev.dto;

import br.com.fcamara.teste.dev.entity.Endereco;
import lombok.Getter;

@Getter
public class EnderecoDto {
	private final String logradouro;

	private final String numero;

	private final String cidade;

	private final String estado;

	public EnderecoDto(Endereco endereco) {
		this.logradouro = endereco.getLogradouro();
		this.numero = endereco.getNumero();
		this.cidade = endereco.getCidade().getNomeCidade();
		this.estado = endereco.getCidade().getEstado().getNomeEstado();
	}
}

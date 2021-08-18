package br.com.fcamara.teste.dev.dto;

import br.com.fcamara.teste.dev.entity.Endereco;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class EnderecoDto {
	private String logradouro;

	private String numero;

	private String cidade;

	private String estado;

	public EnderecoDto(Endereco endereco) {
		this.logradouro = endereco.getLogradouro();
		this.numero = endereco.getNumero();
		this.cidade = endereco.getCidade().getNomeCidade();
		this.estado = endereco.getCidade().getEstado().getNomeEstado();
	}
}

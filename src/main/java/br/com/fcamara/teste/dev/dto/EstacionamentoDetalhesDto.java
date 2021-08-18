package br.com.fcamara.teste.dev.dto;

import br.com.fcamara.teste.dev.entity.Estacionamento;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class EstacionamentoDetalhesDto extends EstacionamentoDto {
	private EnderecoDto endereco;

	public EstacionamentoDetalhesDto(Estacionamento estacionamento) {
		super(estacionamento);
		this.endereco = new EnderecoDto(estacionamento.getEndereco());
	}
}

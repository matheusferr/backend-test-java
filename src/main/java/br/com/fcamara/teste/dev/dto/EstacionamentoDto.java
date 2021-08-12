package br.com.fcamara.teste.dev.dto;

import br.com.fcamara.teste.dev.entity.Estacionamento;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class EstacionamentoDto {
	private Integer id;

	private String nome;

	private String cnpj;

	private String telefone;

	private Integer vagasCarro;

	private Integer VagasMoto;

	public static List<EstacionamentoDto> convertList(List<Estacionamento> estacionamentos) {
		return estacionamentos.stream().map(EstacionamentoDto::new).collect(Collectors.toList());
	}

	public EstacionamentoDto(Estacionamento estacionamento) {
		this.id = estacionamento.getId();
		this.nome = estacionamento.getNomeEstacionamento();
		this.cnpj = estacionamento.getCnpj().getCnpjValue();
		this.telefone = estacionamento.getTelefones().get(0).getTelefoneValue();
		this.vagasCarro = estacionamento.getVagasCarro();
		this.VagasMoto = estacionamento.getVagasMoto();
	}
}

package br.com.fcamara.teste.dev.dto;

import br.com.fcamara.teste.dev.entity.Telefone;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class TelefoneDto {
	private String telefone;

	public TelefoneDto(Telefone telefone) {
		this.telefone = telefone.getTelefoneValue();
	}

	public static List<String> convertListToString(List<Telefone> telefones) {
		return telefones.stream().map(
				(telefone) -> new TelefoneDto(telefone).getTelefone()
		).collect(Collectors.toList());
	}
}

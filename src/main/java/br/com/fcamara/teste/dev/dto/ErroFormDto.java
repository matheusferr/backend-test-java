package br.com.fcamara.teste.dev.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ErroFormDto {
	private String campo;

	private String erro;
}

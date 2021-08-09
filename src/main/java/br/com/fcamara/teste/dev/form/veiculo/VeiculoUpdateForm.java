package br.com.fcamara.teste.dev.form.veiculo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VeiculoUpdateForm {
	@NotNull
	@NotEmpty
	private String cor;
}

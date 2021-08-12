package br.com.fcamara.teste.dev.form.veiculo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VeiculoUpdateForm {
	@NotNull
	@NotEmpty
	private String cor;
}

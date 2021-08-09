package br.com.fcamara.teste.dev.form.vaga;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class VagaForm {
	@NotNull
	@NotEmpty
	@Pattern(regexp = "^[A-Z]{3}[0-9][A-Z][0-9]{2}$", flags = Pattern.Flag.CASE_INSENSITIVE)
	private String placa;

	@NotNull
	@NotEmpty
	@Pattern(regexp = "^\\d{14}$")
	private String cnpj;
}

package br.com.fcamara.teste.dev.form.contato;

import br.com.fcamara.teste.dev.entity.Telefone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TelefoneForm {
	@NotNull
	@NotEmpty
	@Pattern(regexp = "^\\d{11}|\\d{10}$")
	private String telefone;

	public Telefone toTelefone() {
		return new Telefone(this.telefone);
	}
}

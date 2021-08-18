package br.com.fcamara.teste.dev.entity.valueObject;

import br.com.fcamara.teste.dev.exception.PlacaInvalidaException;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@NoArgsConstructor
@EqualsAndHashCode
public class Placa {
	private String placa;

	public Placa(String placa) {
		this.validate(placa);

		this.placa = placa.toUpperCase();
	}

	private void validate(String placa) {
		if(!Pattern.compile("^[A-Z]{3}[0-9][A-Z][0-9]{2}$", Pattern.CASE_INSENSITIVE).matcher(placa).matches())
			throw new PlacaInvalidaException();
	}

	public void setPlaca(String placa) {
		this.validate(placa);

		this.placa = placa;
	}

	public String getPlacaValue() {
		return placa;
	}
}

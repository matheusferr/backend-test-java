package br.com.fcamara.teste.dev.entity.valueObject;

import br.com.fcamara.teste.dev.exception.PlacaInvalidaException;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.regex.Pattern;

@NoArgsConstructor
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

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Placa that = (Placa) o;

		return Objects.equals(placa, that.placa);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());

		return result;
	}
}

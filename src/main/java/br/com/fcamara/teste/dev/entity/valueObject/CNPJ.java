package br.com.fcamara.teste.dev.entity.valueObject;

import br.com.fcamara.teste.dev.exception.CNPJInvalidoException;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode
public class CNPJ {
	private String cnpj;

	public CNPJ(String cnpj) {
		this.validate(cnpj);

		this.cnpj = cnpj;
	}

	private int[] convertStringToIntArray(String cnpj, Integer start, Integer end) {
		if(end != null) return Arrays.stream(cnpj.substring(start, end).split(""))
				.mapToInt(Integer::parseInt).toArray();

		return Arrays.stream(cnpj.substring(start).split("")).mapToInt(Integer::parseInt).toArray();
	}

	private int calculate(int[] digits) {
		int counter = 0;

		List<Integer> multipliers = new ArrayList<>(Arrays.asList(5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2));

		if(digits.length == 13)
			multipliers.add(0, 6);


		for(int i = 0; i < multipliers.size(); i++) {
			counter += digits[i] * multipliers.get(i);
		}

		int mod = counter % 11;

		return mod < 2 ? 0 : 11 - mod;
	}

	private void validate(String cnpj) {
		if(!cnpj.matches("^\\d{14}$")) throw new CNPJInvalidoException("formato inválido");

		int[] digits = this.convertStringToIntArray(cnpj, 0, cnpj.length() - 2);

		int[] verifierDigits = this.convertStringToIntArray(cnpj, cnpj.length() - 2, null);

		if(calculate(digits) != verifierDigits[0]) throw new CNPJInvalidoException("número inválido");

		digits = this.convertStringToIntArray(cnpj, 0, cnpj.length() - 1);

		if(calculate(digits) != verifierDigits[1]) throw new CNPJInvalidoException("número inválido");
	}

	public void setCnpj(String cnpj) {
		this.validate(cnpj);

		this.cnpj = cnpj;
	}

	public String getCnpjValue() {
		return cnpj;
	}
}

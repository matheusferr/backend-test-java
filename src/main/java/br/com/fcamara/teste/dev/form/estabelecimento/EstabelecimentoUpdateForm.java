package br.com.fcamara.teste.dev.form.estabelecimento;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
public class EstabelecimentoUpdateForm {
	private String nome;

	@Min(1)
	private Integer vagasCarro;

	@Min(1)
	private Integer vagasMoto;
}

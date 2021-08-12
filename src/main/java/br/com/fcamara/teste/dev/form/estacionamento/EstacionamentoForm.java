package br.com.fcamara.teste.dev.form.estacionamento;

import br.com.fcamara.teste.dev.entity.Endereco;
import br.com.fcamara.teste.dev.entity.Estacionamento;
import br.com.fcamara.teste.dev.entity.Telefone;
import br.com.fcamara.teste.dev.entity.valueObject.CNPJ;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
public class EstacionamentoForm {
	@NotNull
	@NotEmpty
	private String nome;

	@NotNull
	@NotEmpty
	@Pattern(regexp = "^\\d{14}$")
	private String cnpj;

	@NotNull
	@NotEmpty
	private String logradouro;

	@NotNull
	@NotEmpty
	@Pattern(regexp = "^s/n|\\d$", flags = Pattern.Flag.CASE_INSENSITIVE)
	private String numero;

	@NotNull
	@NotEmpty
	private String cidade;

	@NotNull
	@NotEmpty
	private String estado;

	@NotNull
	@NotEmpty
	@Pattern(regexp = "^\\d{11}|\\d{10}$")
	private String telefone;

	@NotNull
	@Min(1)
	private Integer vagasCarro;

	@NotNull
	@Min(1)
	private Integer vagasMoto;

	public Estacionamento toEstabelecimento(Endereco endereco, CNPJ cnpj, Telefone telefone) {
		return new Estacionamento(this.nome, cnpj, endereco, telefone, vagasCarro, vagasMoto);
	}
}

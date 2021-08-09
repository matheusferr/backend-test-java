package br.com.fcamara.teste.dev.form.veiculo;

import br.com.fcamara.teste.dev.entity.Cor;
import br.com.fcamara.teste.dev.entity.Modelo;
import br.com.fcamara.teste.dev.entity.Veiculo;
import br.com.fcamara.teste.dev.entity.enums.VeiculoTipo;
import br.com.fcamara.teste.dev.entity.valueObject.Placa;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class VeiculoForm {
	@NotNull
	@NotEmpty
	private String marca;

	@NotNull
	@NotEmpty
	private String modelo;

	@NotNull
	@NotEmpty
	private String cor;

	@NotNull
	@NotEmpty
	@Pattern(regexp = "^[A-Z]{3}[0-9][A-Z][0-9]{2}$", flags = Pattern.Flag.CASE_INSENSITIVE)
	private String placa;

	@NotNull
	@NotEmpty
	private String tipo;

	public VeiculoForm(String marca, String modelo, String cor, String placa, String tipo) {
		this.marca = marca;
		this.modelo = modelo;
		this.cor = cor;
		this.placa = placa;
		this.tipo = tipo.toUpperCase();
	}

	public Veiculo toVeiculo(Modelo modelo, Cor cor, VeiculoTipo tipo) {
		return new Veiculo(modelo, cor, new Placa(this.placa), tipo);
	}
}

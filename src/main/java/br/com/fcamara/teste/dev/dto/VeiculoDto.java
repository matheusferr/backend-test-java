package br.com.fcamara.teste.dev.dto;

import br.com.fcamara.teste.dev.entity.Veiculo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class VeiculoDto {
	private Integer id;

	private String marca;

	private String modelo;

	private String cor;

	private String placa;

	private String tipo;

	public VeiculoDto(Veiculo veiculo) {
		this.id = veiculo.getId();
		this.marca = veiculo.getModelo().getMarca().getNomeMarca();
		this.modelo = veiculo.getModelo().getNomeModelo();
		this.cor = veiculo.getCor().getNomeCor();
		this.placa = veiculo.getPlaca().getPlacaValue();
		this.tipo = veiculo.getTipo().getTipoValue();
	}

	public static List<VeiculoDto> convertList(List<Veiculo> veiculos) {
		return veiculos.stream().map(VeiculoDto::new).collect(Collectors.toList());
	}
}

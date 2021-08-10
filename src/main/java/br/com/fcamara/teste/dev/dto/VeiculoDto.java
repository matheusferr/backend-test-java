package br.com.fcamara.teste.dev.dto;

import br.com.fcamara.teste.dev.entity.Veiculo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class VeiculoDto {
	private Integer id;

	private String marca;

	private String modelo;

	private String cor;

	private String placa;

	private String tipo;

	public static List<VeiculoDto> convertList(List<Veiculo> veiculos) {
		return veiculos.stream().map(VeiculoDto::new).collect(Collectors.toList());
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		VeiculoDto that = (VeiculoDto) o;

		return this.marca.equals(that.getMarca()) && this.modelo.equals(that.getModelo())
				&& this.cor.equals(that.getCor()) && this.placa.equals(that.getPlaca())
				&& this.tipo.equals(that.getTipo());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());

		return result;
	}

	public VeiculoDto(Veiculo veiculo) {
		this.id = veiculo.getId();
		this.marca = veiculo.getModelo().getMarca().getNomeMarca();
		this.modelo = veiculo.getModelo().getNomeModelo();
		this.cor = veiculo.getCor().getNomeCor();
		this.placa = veiculo.getPlaca().getPlacaValue();
		this.tipo = veiculo.getTipo().getTipoValue();
	}
}

package br.com.fcamara.teste.dev.form.veiculo;

import br.com.fcamara.teste.dev.entity.Cor;
import br.com.fcamara.teste.dev.entity.Modelo;
import br.com.fcamara.teste.dev.entity.Veiculo;
import br.com.fcamara.teste.dev.entity.enums.VeiculoTipo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
public class VeiculoForm {
    @NotNull @NotEmpty
    private String marca;

    @NotNull @NotEmpty
    private String modelo;

    @NotNull @NotEmpty
    private String cor;

    @NotNull @NotEmpty
    private String placa;

    @NotNull @NotEmpty
    private String tipo;

    public Veiculo toVeiculo(Modelo modelo, Cor cor, VeiculoTipo tipo){
        return new Veiculo(modelo, cor, this.placa, tipo);
    }
}

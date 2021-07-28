package br.com.fcamara.teste.dev.form.veiculo;

import br.com.fcamara.teste.dev.entity.Cor;
import br.com.fcamara.teste.dev.entity.Modelo;
import br.com.fcamara.teste.dev.entity.Veiculo;
import br.com.fcamara.teste.dev.entity.enums.VeiculoTipo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VeiculoUpdateForm {
    @NotNull @NotEmpty
    private String cor;

    public Veiculo toVeiculo(Veiculo veiculo, Cor cor){

        return veiculo;
    }
}

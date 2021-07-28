package br.com.fcamara.teste.dev.form.estabelecimento;

import br.com.fcamara.teste.dev.entity.Telefone;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class EstabelecimentoTelefoneForm {
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^\\d{11}|\\d{10}$")
    private String telefone;

    public Telefone toTelefone() {
        return new Telefone(this.telefone);
    }
}

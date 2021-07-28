package br.com.fcamara.teste.dev.form.estabelecimento;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EstabelecimentoUpdateForm {
    @NotNull
    @NotEmpty
    private String nome;
}

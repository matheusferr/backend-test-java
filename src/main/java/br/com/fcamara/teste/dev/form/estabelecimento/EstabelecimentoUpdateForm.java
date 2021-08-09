package br.com.fcamara.teste.dev.form.estabelecimento;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EstabelecimentoUpdateForm {
    private String nome;

    @Min(1)
    private Integer vagasCarro;

    @Min(1)
    private Integer vagasMoto;
}

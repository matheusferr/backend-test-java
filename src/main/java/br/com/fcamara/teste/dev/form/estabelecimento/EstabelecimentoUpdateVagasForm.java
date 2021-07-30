package br.com.fcamara.teste.dev.form.estabelecimento;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Setter
@Getter
public class EstabelecimentoUpdateVagasForm {
    @Min(1)
    private Integer vagasCarro;

    @Min(1)
    private Integer vagasMoto;
}

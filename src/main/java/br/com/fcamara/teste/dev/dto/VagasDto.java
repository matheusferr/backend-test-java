package br.com.fcamara.teste.dev.dto;

import br.com.fcamara.teste.dev.entity.Estabelecimento;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class VagasDto {
    private Integer vagasCarro;

    private Integer vagasMoto;

    public VagasDto(Estabelecimento estabelecimento) {
        this.vagasCarro = estabelecimento.getVagasCarro();
        this.vagasMoto = estabelecimento.getVagasMoto();
    }
}

package br.com.fcamara.teste.dev.dto;

import br.com.fcamara.teste.dev.entity.Vagas;
import lombok.Getter;

@Getter
public class VagasDto {
    private Integer vagasCarro;

    private Integer vagasMoto;

    public VagasDto(Vagas vagas) {
        this.vagasCarro = vagas.getVagasCarro();
        this.vagasMoto = vagas.getVagasMoto();
    }
}

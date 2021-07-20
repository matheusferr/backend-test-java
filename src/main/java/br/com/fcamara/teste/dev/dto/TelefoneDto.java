package br.com.fcamara.teste.dev.dto;

import br.com.fcamara.teste.dev.entity.Telefone;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TelefoneDto {
    private final String telefone;

    public TelefoneDto(Telefone telefone) {
        this.telefone = telefone.getTelefoneValue();
    }

    public static List<TelefoneDto> convertList(List<Telefone> telefones){
        return telefones.stream().map(TelefoneDto::new).collect(Collectors.toList());
    }
}

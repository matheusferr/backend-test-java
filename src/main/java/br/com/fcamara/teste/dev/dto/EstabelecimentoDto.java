package br.com.fcamara.teste.dev.dto;

import br.com.fcamara.teste.dev.entity.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class EstabelecimentoDto {
    private String nome;

    private String cnpj;

    private EnderecoDto endereco;

    private String telefone;

    public static List<EstabelecimentoDto> convertList(List<Estabelecimento> estabelecimentos) {
        return estabelecimentos.stream().map(EstabelecimentoDto::new).collect(Collectors.toList());
    }

    public EstabelecimentoDto(Estabelecimento estabelecimento) {
        this.nome = estabelecimento.getNome();
        this.cnpj = estabelecimento.getCnpj().getCnpjValue();
        this.endereco = new EnderecoDto(estabelecimento.getEndereco());
        this.telefone = estabelecimento.getTelefone().getTelefoneValue();
    }
}

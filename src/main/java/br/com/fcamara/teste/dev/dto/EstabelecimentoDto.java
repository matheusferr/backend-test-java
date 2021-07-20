package br.com.fcamara.teste.dev.dto;

import br.com.fcamara.teste.dev.entity.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class EstabelecimentoDto {
    private Integer id;

    private String nome;

    private String cnpj;

    private EnderecoDto endereco;

    private List<TelefoneDto> telefones;

    public static List<EstabelecimentoDto> convertList(List<Estabelecimento> estabelecimentos) {
        return estabelecimentos.stream().map(EstabelecimentoDto::new).collect(Collectors.toList());
    }

    public EstabelecimentoDto(Estabelecimento estabelecimento) {
        this.id = estabelecimento.getId();
        this.nome = estabelecimento.getNomeEstabelecimento();
        this.cnpj = estabelecimento.getCnpj().getCnpjValue();
        this.telefones = TelefoneDto.convertList(estabelecimento.getTelefones());
        this.endereco = new EnderecoDto(estabelecimento.getEndereco());
    }
}

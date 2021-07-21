package br.com.fcamara.teste.dev.dto;

import br.com.fcamara.teste.dev.entity.*;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EstabelecimentoDto {
    private final Integer id;

    private final String nome;

    private final String cnpj;

    private final EnderecoDto endereco;

    private final List<String> telefones;

    public static List<EstabelecimentoDto> convertList(List<Estabelecimento> estabelecimentos) {
        return estabelecimentos.stream().map(EstabelecimentoDto::new).collect(Collectors.toList());
    }

    public EstabelecimentoDto(Estabelecimento estabelecimento) {
        this.id = estabelecimento.getId();
        this.nome = estabelecimento.getNomeEstabelecimento();
        this.cnpj = estabelecimento.getCnpj().getCnpjValue();
        this.telefones = TelefoneDto.convertListToString(estabelecimento.getTelefones());
        this.endereco = new EnderecoDto(estabelecimento.getEndereco());
    }
}

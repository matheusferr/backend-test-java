package br.com.fcamara.teste.dev.entity;

import br.com.fcamara.teste.dev.entity.converter.CNPJCoverter;
import br.com.fcamara.teste.dev.entity.valueObject.CNPJ;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Estabelecimentos")
public class Estabelecimento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Convert(converter = CNPJCoverter.class)
    private CNPJ cnpj;

    @ManyToOne
    private Endereco endereco;

    @ManyToOne
    private Telefone telefone;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Veiculo> veiculos;

    @OneToOne
    private Vagas vagas;
}

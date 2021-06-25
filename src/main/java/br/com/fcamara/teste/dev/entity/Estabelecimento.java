package br.com.fcamara.teste.dev.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Estabelecimentos")
public class Estabelecimento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String cnpj;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Endereco endereco;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Telefone telefone;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Veiculo> veiculos;

    private Integer vagasCarro;

    private Integer vagasMoto;
}

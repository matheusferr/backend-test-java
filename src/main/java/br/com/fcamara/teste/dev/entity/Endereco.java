package br.com.fcamara.teste.dev.entity;

import javax.persistence.*;

@Entity
@Table(name = "Enderecos")
public class Endereco {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String logradouro;

    private Integer numero;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cidade cidade;
}

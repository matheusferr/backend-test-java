package br.com.fcamara.teste.dev.entity;

import javax.persistence.*;

@Entity
@Table(name = "Cidades")
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomeCidade;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Estado estado;
}

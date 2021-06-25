package br.com.fcamara.teste.dev.entity;

import javax.persistence.*;

@Entity
@Table(name = "Modelos")
public class Modelo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomeModelo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Marca marca;
}

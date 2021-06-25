package br.com.fcamara.teste.dev.entity;

import javax.persistence.*;

@Entity
@Table(name = "Estados")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomeEstado;
}

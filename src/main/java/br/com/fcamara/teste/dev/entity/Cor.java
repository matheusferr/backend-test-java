package br.com.fcamara.teste.dev.entity;

import javax.persistence.*;

@Entity
@Table(name = "Cores")
public class Cor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomeCor;
}

package br.com.fcamara.teste.dev.entity;

import javax.persistence.*;

@Entity
@Table(name = "Marcas")
public class Marca {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomeMarca;
}

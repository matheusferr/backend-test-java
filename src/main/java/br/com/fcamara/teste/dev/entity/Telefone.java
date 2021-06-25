package br.com.fcamara.teste.dev.entity;

import javax.persistence.*;

@Entity
@Table(name = "Telefones")
public class Telefone {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String telefone;
}

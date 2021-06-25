package br.com.fcamara.teste.dev.entity;

import br.com.fcamara.teste.dev.entity.enums.VeiculoTipo;

import javax.persistence.*;

@Entity
@Table(name = "Veiculos")
public class Veiculo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Modelo modelo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cor cor;

    private String placa;

    @Enumerated(EnumType.STRING)
    private VeiculoTipo tipo;
}

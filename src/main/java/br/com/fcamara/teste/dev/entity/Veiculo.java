package br.com.fcamara.teste.dev.entity;

import br.com.fcamara.teste.dev.entity.enums.VeiculoTipo;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Veiculos")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Modelo modelo;

    @ManyToOne
    private Cor cor;

    @Column(unique = true)
    private String placa;

    @Enumerated(EnumType.STRING)
    private VeiculoTipo tipo;

    public Veiculo(Modelo modelo, Cor cor, String placa, VeiculoTipo tipo) {
        this.modelo = modelo;
        this.cor = cor;
        this.placa = placa;
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return modelo.equals(veiculo.modelo) && cor.equals(veiculo.cor) && placa.equals(veiculo.placa) && tipo == veiculo.tipo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());

        return result;
    }

    @PrePersist
    private void prePersist() {
        this.placa = this.placa.toUpperCase();
    }
}

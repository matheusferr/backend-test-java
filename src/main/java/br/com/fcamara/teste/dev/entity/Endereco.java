package br.com.fcamara.teste.dev.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Enderecos")
public class Endereco {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String logradouro;

    private Integer numero;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cidade cidade;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;

        return Objects.equals(id, endereco.id) && Objects.equals(logradouro, endereco.logradouro) && Objects.equals(numero, endereco.numero) && Objects.equals(cidade, endereco.cidade);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());

        return result;
    }

    public Endereco(String logradouro, Integer numero, Cidade cidade) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.cidade = cidade;
    }
}

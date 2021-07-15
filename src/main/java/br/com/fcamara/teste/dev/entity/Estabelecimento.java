package br.com.fcamara.teste.dev.entity;

import br.com.fcamara.teste.dev.entity.converter.CNPJCoverter;
import br.com.fcamara.teste.dev.entity.valueObject.CNPJ;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Estabelecimentos")
public class Estabelecimento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Convert(converter = CNPJCoverter.class)
    private CNPJ cnpj;

    @ManyToOne
    private Endereco endereco;

    @ManyToOne
    private Telefone telefone;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Veiculo> veiculos;

    @OneToOne
    private Vagas vagas;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estabelecimento that = (Estabelecimento) o;

        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(cnpj, that.cnpj)
                && Objects.equals(endereco, that.endereco) && Objects.equals(telefone, that.telefone);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());

        return result;
    }

    public Estabelecimento(String nome, CNPJ cnpj, Endereco endereco, Telefone telefone) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefone = telefone;
    }
}

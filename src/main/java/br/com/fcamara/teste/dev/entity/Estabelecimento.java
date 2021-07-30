package br.com.fcamara.teste.dev.entity;

import br.com.fcamara.teste.dev.entity.converter.CNPJCoverter;
import br.com.fcamara.teste.dev.entity.valueObject.CNPJ;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Estabelecimentos")
public class Estabelecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomeEstabelecimento;

    @Convert(converter = CNPJCoverter.class)
    @Column(unique = true)
    private CNPJ cnpj;

    @OneToOne
    private Endereco endereco;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Telefone> telefones = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Vagas vagas;

    public Estabelecimento(String nomeEstabelecimento, CNPJ cnpj, Endereco endereco, Telefone telefone, Vagas vagas) {
        this.nomeEstabelecimento = nomeEstabelecimento;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefones = new ArrayList<>(List.of(telefone));
        this.vagas = vagas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estabelecimento that = (Estabelecimento) o;

        return Objects.equals(nomeEstabelecimento, that.nomeEstabelecimento) && Objects.equals(cnpj, that.cnpj)
                && Objects.equals(endereco, that.endereco);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());

        return result;
    }

    @PrePersist
    @PreUpdate
    private void prePersist() {
        this.nomeEstabelecimento = this.nomeEstabelecimento.toUpperCase();
    }
}

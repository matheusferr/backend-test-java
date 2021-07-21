package br.com.fcamara.teste.dev.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "Telefones")
public class Telefone {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String numeroTelefone;

    private void validate(String telefone){
        if (!telefone.matches("^\\d{11}|\\d{10}$"))
            throw new IllegalArgumentException("Telefone invalido");
    }

    public String getTelefoneValue() {
        return this.numeroTelefone;
    }

    public void setTelefone(String telefone) {
        this.validate(telefone);
        this.numeroTelefone = telefone;
    }

    public Telefone(String telefone) {
        this.validate(telefone);
        this.numeroTelefone = telefone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefone that = (Telefone) o;

        return numeroTelefone.equals(that.numeroTelefone);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());

        return result;
    }
}

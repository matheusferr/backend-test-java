package br.com.fcamara.teste.dev.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "Telefones")
public class Telefone {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String telefone;

    private void validate(String telefone){
        if (!telefone.matches("^\\d{10}|\\d{11}$"))
            throw new IllegalArgumentException("Telefone invalido");
    }

    public String getTelefoneValue() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.validate(telefone);
        this.telefone = telefone;
    }

    public Telefone(String telefone) {
        this.validate(telefone);
        this.telefone = telefone;
    }
}

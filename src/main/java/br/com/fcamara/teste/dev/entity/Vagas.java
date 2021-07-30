package br.com.fcamara.teste.dev.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Vagas")
public class Vagas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer vagasCarro;

    private Integer vagasMoto;

    public Vagas(Integer vagasCarro, Integer vagasMoto) {
        this.vagasCarro = vagasCarro;
        this.vagasMoto = vagasMoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Vagas vagas = (Vagas) o;

        return Objects.equals(id, vagas.id) && Objects.equals(vagasCarro, vagas.vagasCarro) &&
                Objects.equals(vagasMoto, vagas.vagasMoto);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());

        return result;
    }
}

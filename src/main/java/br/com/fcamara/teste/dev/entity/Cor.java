package br.com.fcamara.teste.dev.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Cores")
public class Cor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NonNull
	private String nomeCor;

	@PrePersist
	private void prePersist() {
		this.nomeCor = this.nomeCor.toUpperCase();
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Cor cor = (Cor) o;

		return nomeCor.equals(cor.nomeCor);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());

		return result;
	}

	public Cor(String nomeCor) {
		this.nomeCor = nomeCor;
	}
}

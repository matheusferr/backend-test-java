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
public class Estacionamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@Convert(converter = CNPJCoverter.class)
	@Column(unique = true)
	private CNPJ cnpj;

	@OneToOne
	private Endereco endereco;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Telefone> telefones = new ArrayList<>();

	private Integer vagasCarro;

	private Integer vagasMoto;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Vaga> vagas;

	public Estacionamento(String nome, CNPJ cnpj, Endereco endereco, Telefone telefone,
	                      Integer vagasCarro, Integer vagasMoto) {
		this.nome = nome;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.telefones = new ArrayList<>(List.of(telefone));
		this.vagasCarro = vagasCarro;
		this.vagasMoto = vagasMoto;
		this.vagas = new ArrayList<>();
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Estacionamento that = (Estacionamento) o;

		return Objects.equals(id, that.id) && Objects.equals(nome, that.nome)
				&& Objects.equals(cnpj, that.cnpj) && Objects.equals(endereco, that.endereco)
				&& Objects.equals(vagas, that.vagas) && Objects.equals(vagasCarro, that.vagasCarro)
				&& Objects.equals(vagasMoto, that.vagasMoto);
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
		this.nome = this.nome.toUpperCase();
	}
}

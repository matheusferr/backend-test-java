package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Cidade;
import br.com.fcamara.teste.dev.entity.Endereco;
import br.com.fcamara.teste.dev.entity.Estado;
import br.com.fcamara.teste.dev.repository.EnderecoRepository;
import br.com.fcamara.teste.dev.service.definition.EnderecoService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoServiceImpl implements EnderecoService {
	private final EnderecoRepository enderecoRepository;
	private final CidadeServiceImpl cidadeServiceImpl;
	private final EstadoServiceImpl estadoServiceImpl;

	public EnderecoServiceImpl(EnderecoRepository enderecoRepository, CidadeServiceImpl cidadeServiceImpl, EstadoServiceImpl estadoServiceImpl) {
		this.enderecoRepository = enderecoRepository;
		this.cidadeServiceImpl = cidadeServiceImpl;
		this.estadoServiceImpl = estadoServiceImpl;
	}

	@Override
	public Endereco findOrCreate(String logradouro, String numero, String nomeCidade, String nomeEstado) {
		Optional<Endereco> enderecoOptional = this.enderecoRepository.findByLogradouroAndNumero(logradouro, numero);

		if(enderecoOptional.isEmpty()) {
			Endereco endereco;
			Optional<Cidade> cidadeOptional = this.cidadeServiceImpl.findByNomeCidade(nomeCidade);

			if(cidadeOptional.isPresent()) {
				endereco = new Endereco(logradouro, numero, cidadeOptional.get());

			}
			else {
				Estado estado = this.estadoServiceImpl.findByNomeOrCreate(nomeEstado);

				Cidade cidade = this.cidadeServiceImpl.create(nomeCidade, estado);

				endereco = new Endereco(logradouro, numero, cidade);
			}

			return this.enderecoRepository.save(endereco);
		}

		return enderecoOptional.get();
	}
}

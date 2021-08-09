package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.*;
import br.com.fcamara.teste.dev.entity.valueObject.CNPJ;
import br.com.fcamara.teste.dev.exception.DadosInvalidosException;
import br.com.fcamara.teste.dev.exception.OperacaoInvalidaException;
import br.com.fcamara.teste.dev.form.estabelecimento.EstabelecimentoForm;
import br.com.fcamara.teste.dev.form.estabelecimento.EstabelecimentoTelefoneForm;
import br.com.fcamara.teste.dev.form.estabelecimento.EstabelecimentoUpdateForm;
import br.com.fcamara.teste.dev.repository.EstabelecimentoRepository;
import br.com.fcamara.teste.dev.service.definition.EstabelecimentoService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class EstabelecimentoServiceImpl implements EstabelecimentoService {
	private final EnderecoServiceImpl enderecoServiceImpl;
	private final TelefoneServiceImpl telefoneServiceImpl;
	private final EstabelecimentoRepository estabelecimentoRepository;

	public EstabelecimentoServiceImpl(EnderecoServiceImpl enderecoServiceImpl, TelefoneServiceImpl telefoneServiceImpl,
	                                  EstabelecimentoRepository estabelecimentoRepository) {
		this.enderecoServiceImpl = enderecoServiceImpl;
		this.telefoneServiceImpl = telefoneServiceImpl;
		this.estabelecimentoRepository = estabelecimentoRepository;
	}

	private Estabelecimento getEstabelecimento(Integer id) {
		Optional<Estabelecimento> estabelecimento = this.estabelecimentoRepository.findById(id);

		if(estabelecimento.isEmpty()) throw new EntityNotFoundException();

		return estabelecimento.get();
	}

	@Override
	public List<Estabelecimento> index() {
		return this.estabelecimentoRepository.findAll();
	}

	@Override
	public Estabelecimento findById(Integer id) {
		return this.getEstabelecimento(id);
	}

	@Override
	public Estabelecimento findByCnpj(String cnpj) {
		Optional<Estabelecimento> estabelecimento = this.estabelecimentoRepository.findByCnpj(new CNPJ(cnpj));

		if(estabelecimento.isEmpty()) throw new EntityNotFoundException();

		return estabelecimento.get();
	}

	@Override
	public Estabelecimento create(EstabelecimentoForm estabelecimentoForm) {
		CNPJ cnpj = new CNPJ(estabelecimentoForm.getCnpj());

		Telefone telefone = this.telefoneServiceImpl.findByNumeroOrCreate(estabelecimentoForm.getTelefone());

		Endereco endereco = this.enderecoServiceImpl.findOrCreate(
				estabelecimentoForm.getLogradouro(), estabelecimentoForm.getNumero(), estabelecimentoForm.getCidade(),
				estabelecimentoForm.getEstado()
		);

		Estabelecimento estabelecimento = estabelecimentoForm.toEstabelecimento(endereco, cnpj, telefone);

		return this.estabelecimentoRepository.save(estabelecimento);
	}

	@Override
	public Estabelecimento update(Integer id, EstabelecimentoUpdateForm estabelecimentoUpdateForm) {
		Estabelecimento estabelecimento = this.getEstabelecimento(id);

		if(!estabelecimentoUpdateForm.getNome().equals(""))
			estabelecimento.setNomeEstabelecimento(estabelecimentoUpdateForm.getNome());

		if(estabelecimentoUpdateForm.getVagasCarro() == null && estabelecimentoUpdateForm.getVagasMoto() == null)
			throw new DadosInvalidosException("os valores das vagas de carros e motos não podem ser nulos");

		if(estabelecimentoUpdateForm.getVagasCarro() != null)
			estabelecimento.setVagasCarro(estabelecimentoUpdateForm.getVagasCarro());

		if(estabelecimentoUpdateForm.getVagasMoto() != null)
			estabelecimento.setVagasMoto(estabelecimentoUpdateForm.getVagasMoto());

		return this.estabelecimentoRepository.save(estabelecimento);
	}

	@Override
	public void updateVagas(Estabelecimento estabelecimento) {
		this.estabelecimentoRepository.save(estabelecimento);
	}

	@Override
	public List<Telefone> getPhones(Integer id) {
		Estabelecimento estabelecimento = this.getEstabelecimento(id);

		return estabelecimento.getTelefones();
	}

	@Override
	public Estabelecimento addPhone(Integer id, EstabelecimentoTelefoneForm estabelecimentoTelefoneForm) {
		Estabelecimento estabelecimento = this.getEstabelecimento(id);

		Telefone telefone = this.telefoneServiceImpl.findByNumeroOrCreate(estabelecimentoTelefoneForm.getTelefone());

		if(estabelecimento.getTelefones().contains(telefone)) throw new OperacaoInvalidaException(
				"telefone já vinculado ao estabelecimento"
		);

		estabelecimento.getTelefones().add(telefone);

		return this.estabelecimentoRepository.save(estabelecimento);
	}

	@Override
	public void removePhone(Integer id, EstabelecimentoTelefoneForm estabelecimentoTelefoneForm) {
		Estabelecimento estabelecimento = this.getEstabelecimento(id);

		Telefone telefone = this.telefoneServiceImpl.findByNumero(estabelecimentoTelefoneForm.getTelefone());

		if(!estabelecimento.getTelefones().contains(telefone)) throw new OperacaoInvalidaException(
				"telefone não vinculado ao estabelecimento"
		);

		estabelecimento.getTelefones().remove(telefone);

		this.estabelecimentoRepository.save(estabelecimento);
	}

	@Override
	public void destroy(Integer id) {
		Estabelecimento estabelecimento = this.getEstabelecimento(id);

		this.estabelecimentoRepository.delete(estabelecimento);
	}
}

package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Endereco;
import br.com.fcamara.teste.dev.entity.Estacionamento;
import br.com.fcamara.teste.dev.entity.Telefone;
import br.com.fcamara.teste.dev.entity.valueObject.CNPJ;
import br.com.fcamara.teste.dev.exception.DadosInvalidosException;
import br.com.fcamara.teste.dev.exception.OperacaoInvalidaException;
import br.com.fcamara.teste.dev.form.contato.TelefoneForm;
import br.com.fcamara.teste.dev.form.estacionamento.EstacionamentoForm;
import br.com.fcamara.teste.dev.form.estacionamento.EstacionamentoUpdateForm;
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

	private Estacionamento getEstabelecimento(Integer id) {
		Optional<Estacionamento> estabelecimento = this.estabelecimentoRepository.findById(id);

		if(estabelecimento.isEmpty()) throw new EntityNotFoundException();

		return estabelecimento.get();
	}

	@Override
	public List<Estacionamento> index() {
		return this.estabelecimentoRepository.findAll();
	}

	@Override
	public Estacionamento findById(Integer id) {
		return this.getEstabelecimento(id);
	}

	@Override
	public Estacionamento findByCnpj(String cnpj) {
		Optional<Estacionamento> estabelecimento = this.estabelecimentoRepository.findByCnpj(new CNPJ(cnpj));

		if(estabelecimento.isEmpty()) throw new EntityNotFoundException();

		return estabelecimento.get();
	}

	@Override
	public Estacionamento create(EstacionamentoForm estacionamentoForm) {
		CNPJ cnpj = new CNPJ(estacionamentoForm.getCnpj());

		Telefone telefone = this.telefoneServiceImpl.findByNumeroOrCreate(estacionamentoForm.getTelefone());

		Endereco endereco = this.enderecoServiceImpl.findOrCreate(
				estacionamentoForm.getLogradouro(), estacionamentoForm.getNumero(), estacionamentoForm.getCidade(),
				estacionamentoForm.getEstado()
		);

		Estacionamento estacionamento = estacionamentoForm.toEstabelecimento(endereco, cnpj, telefone);

		return this.estabelecimentoRepository.save(estacionamento);
	}

	@Override
	public Estacionamento update(Integer id, EstacionamentoUpdateForm estacionamentoUpdateForm) {
		Estacionamento estacionamento = this.getEstabelecimento(id);

		if(!estacionamentoUpdateForm.getNome().equals(""))
			estacionamento.setNome(estacionamentoUpdateForm.getNome());

		if(estacionamentoUpdateForm.getVagasCarro() == null && estacionamentoUpdateForm.getVagasMoto() == null)
			throw new DadosInvalidosException("os valores das vagas de carros e motos não podem ser nulos");

		if(estacionamentoUpdateForm.getVagasCarro() != null)
			estacionamento.setVagasCarro(estacionamentoUpdateForm.getVagasCarro());

		if(estacionamentoUpdateForm.getVagasMoto() != null)
			estacionamento.setVagasMoto(estacionamentoUpdateForm.getVagasMoto());

		return this.estabelecimentoRepository.save(estacionamento);
	}

	@Override
	public void updateVagas(Estacionamento estacionamento) {
		this.estabelecimentoRepository.save(estacionamento);
	}

	@Override
	public List<Telefone> getPhones(Integer id) {
		Estacionamento estacionamento = this.getEstabelecimento(id);

		return estacionamento.getTelefones();
	}

	@Override
	public Estacionamento addPhone(Integer id, TelefoneForm telefoneForm) {
		Estacionamento estacionamento = this.getEstabelecimento(id);

		Telefone telefone = this.telefoneServiceImpl.findByNumeroOrCreate(telefoneForm.getTelefone());

		if(estacionamento.getTelefones().contains(telefone)) throw new OperacaoInvalidaException(
				"telefone já vinculado ao estabelecimento"
		);

		estacionamento.getTelefones().add(telefone);

		return this.estabelecimentoRepository.save(estacionamento);
	}

	@Override
	public void removePhone(Integer id, TelefoneForm telefoneForm) {
		Estacionamento estacionamento = this.getEstabelecimento(id);

		Telefone telefone = this.telefoneServiceImpl.findByNumero(telefoneForm.getTelefone());

		if(!estacionamento.getTelefones().contains(telefone)) throw new OperacaoInvalidaException(
				"telefone não vinculado ao estabelecimento"
		);

		estacionamento.getTelefones().remove(telefone);

		this.estabelecimentoRepository.save(estacionamento);
	}

	@Override
	public void destroy(Integer id) {
		Estacionamento estacionamento = this.getEstabelecimento(id);

		this.estabelecimentoRepository.delete(estacionamento);
	}
}

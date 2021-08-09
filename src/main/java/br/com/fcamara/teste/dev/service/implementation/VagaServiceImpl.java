package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Estabelecimento;
import br.com.fcamara.teste.dev.entity.Vaga;
import br.com.fcamara.teste.dev.entity.Veiculo;
import br.com.fcamara.teste.dev.exception.OperacaoInvalidaException;
import br.com.fcamara.teste.dev.form.vaga.VagaForm;
import br.com.fcamara.teste.dev.repository.VagaRepository;
import br.com.fcamara.teste.dev.service.definition.VagaService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VagaServiceImpl implements VagaService {
	private final EstabelecimentoServiceImpl estabelecimentoServiceImpl;
	private final VeiculoServiceImpl veiculoServiceImpl;
	private final VagaRepository vagaRepository;

	public VagaServiceImpl(EstabelecimentoServiceImpl estabelecimentoServiceImpl,
	                       VeiculoServiceImpl veiculoServiceImpl, VagaRepository vagaRepository) {
		this.estabelecimentoServiceImpl = estabelecimentoServiceImpl;
		this.veiculoServiceImpl = veiculoServiceImpl;
		this.vagaRepository = vagaRepository;
	}

	private List<Vaga> getVagas(String cnpj) {
		Estabelecimento estabelecimento = estabelecimentoServiceImpl.findByCnpj(cnpj);
		return estabelecimento.getVagas();
	}

	private boolean isPresent(List<Vaga> vagas, Veiculo veiculo) {
		return vagas.stream().anyMatch(vaga -> vaga.getVeiculo().equals(veiculo));
	}

	private Vaga getVaga(Veiculo veiculo) {
		Optional<Vaga> vaga = this.vagaRepository.findByVeiculoAndSaidaNull(veiculo);

		if(vaga.isEmpty()) throw new EntityNotFoundException();

		return vaga.get();
	}

	@Override
	public Vaga addVehicle(VagaForm vagaForm) {
		Veiculo veiculo = this.veiculoServiceImpl.findByPlaca(vagaForm.getPlaca());

		Optional<Vaga> existente = this.vagaRepository.findByVeiculoAndSaidaNull(veiculo);

		if(existente.isPresent()) throw new OperacaoInvalidaException(
				"veiculo já vinculado a outro estacionamento"
		);

		List<Vaga> vagas = this.getVagas(vagaForm.getCnpj());

		if(this.isPresent(vagas, veiculo)) throw new OperacaoInvalidaException(
				"veiculo já vinculado ao estacionamento"
		);

		Vaga vaga = new Vaga(veiculo);

		vagas.add(vaga);

		return vagaRepository.save(vaga);
	}

	@Override
	public Vaga removeVehicle(VagaForm vagaForm) {
		Veiculo veiculo = veiculoServiceImpl.findByPlaca(vagaForm.getPlaca());
		List<Vaga> vagas = this.getVagas(vagaForm.getCnpj());

		if(!this.isPresent(vagas, veiculo)) throw new OperacaoInvalidaException(
				"veiculo não vinculado ao estacionamento"
		);

		Vaga vaga = this.getVaga(veiculo);

		vagas.remove(vaga);

		vaga.setSaida(LocalDateTime.now());

		return vagaRepository.save(vaga);
	}
}

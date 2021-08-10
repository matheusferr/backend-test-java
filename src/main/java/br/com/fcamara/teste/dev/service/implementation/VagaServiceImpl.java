package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Estabelecimento;
import br.com.fcamara.teste.dev.entity.Tipo;
import br.com.fcamara.teste.dev.entity.Vaga;
import br.com.fcamara.teste.dev.entity.Veiculo;
import br.com.fcamara.teste.dev.entity.enums.VeiculoTipo;
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

	private boolean exists(Veiculo veiculo) {
		Optional<Vaga> existente = this.vagaRepository.findByVeiculoAndSaidaNull(veiculo);

		return existente.isPresent();
	}

	private void hasEmptySlots(Tipo tipo, Integer limiteVagas) {
		Integer vagas = this.vagaRepository.countAllByTipoAndSaidaNull(tipo);

		if(limiteVagas - vagas == 0) throw new OperacaoInvalidaException(
				"não há vagas disponíveis"
		);
	}

	private Vaga getVaga(Veiculo veiculo) {
		Optional<Vaga> vaga = this.vagaRepository.findByVeiculoAndSaidaNull(veiculo);

		if(vaga.isEmpty()) throw new EntityNotFoundException();

		return vaga.get();
	}

	@Override
	public Vaga addVehicle(VagaForm vagaForm) {
		Veiculo veiculo = this.veiculoServiceImpl.findByPlaca(vagaForm.getPlaca());
		Estabelecimento estabelecimento = this.estabelecimentoServiceImpl.findByCnpj(vagaForm.getCnpj());

		if(this.exists(veiculo)) throw new OperacaoInvalidaException(
				"veiculo já vinculado a outro estacionamento"
		);

		if(veiculo.getTipo().getTipoValue().equalsIgnoreCase(VeiculoTipo.CARRO.toString()))
			this.hasEmptySlots(veiculo.getTipo(), estabelecimento.getVagasCarro());

		else
			this.hasEmptySlots(veiculo.getTipo(), estabelecimento.getVagasMoto());


		List<Vaga> vagas = estabelecimento.getVagas();

		Vaga vaga = new Vaga(veiculo);

		vagas.add(vaga);

		return vagaRepository.save(vaga);
	}

	@Override
	public Vaga removeVehicle(VagaForm vagaForm) {
		Veiculo veiculo = veiculoServiceImpl.findByPlaca(vagaForm.getPlaca());
		Estabelecimento estabelecimento = this.estabelecimentoServiceImpl.findByCnpj(vagaForm.getCnpj());

		if(!this.exists(veiculo)) throw new OperacaoInvalidaException(
				"veiculo não vinculado ao estacionamento"
		);

		List<Vaga> vagas = estabelecimento.getVagas();

		Vaga vaga = this.getVaga(veiculo);

		vagas.remove(vaga);

		vaga.setSaida(LocalDateTime.now());

		return vagaRepository.save(vaga);
	}
}

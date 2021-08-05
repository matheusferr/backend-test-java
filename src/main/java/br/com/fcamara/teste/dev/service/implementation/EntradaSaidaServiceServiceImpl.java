package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Estabelecimento;
import br.com.fcamara.teste.dev.entity.Vaga;
import br.com.fcamara.teste.dev.entity.Veiculo;
import br.com.fcamara.teste.dev.exception.OperacaoInvalidaException;
import br.com.fcamara.teste.dev.repository.VagaRepository;
import br.com.fcamara.teste.dev.service.definition.EntradaSaidaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntradaSaidaServiceServiceImpl implements EntradaSaidaService {
    private EstabelecimentoServiceImpl estabelecimentoServiceImpl;
    private VeiculoServiceImpl veiculoServiceImpl;
    private VagaRepository vagaRepository;

    public EntradaSaidaServiceServiceImpl(EstabelecimentoServiceImpl estabelecimentoServiceImpl,
                                          VeiculoServiceImpl veiculoServiceImpl, VagaRepository vagaRepository) {
        this.estabelecimentoServiceImpl = estabelecimentoServiceImpl;
        this.veiculoServiceImpl = veiculoServiceImpl;
        this.vagaRepository = vagaRepository;
    }

    private boolean isPresent(List<Vaga> vagas, Veiculo veiculo) {
        return vagas.stream().anyMatch(vaga -> vaga.getVeiculo().equals(veiculo));
    }

    @Override
    public Vaga addVehicle(String placa, String cnpj) {
        Veiculo veiculo = veiculoServiceImpl.findByPlaca(placa);
        Estabelecimento estabelecimento = estabelecimentoServiceImpl.findByCnpj(cnpj);
        List<Vaga> vagas = estabelecimento.getVagas();

        if (this.isPresent(vagas, veiculo)) throw new OperacaoInvalidaException(
                "veiculo já vinculado ao estacionamento"
        );

        Vaga vaga = new Vaga(veiculo);

        vagas.add(vaga);

        return vagaRepository.save(vaga);
    }

    @Override
    public void removeVehicle(String placa, String cnpj) {
        Veiculo veiculo = veiculoServiceImpl.findByPlaca(placa);
        Estabelecimento estabelecimento = estabelecimentoServiceImpl.findByCnpj(cnpj);
        List<Vaga> vagas = estabelecimento.getVagas();

        if (!this.isPresent(vagas, veiculo)) throw new OperacaoInvalidaException(
                "veiculo não vinculado ao estacionamento"
        );

        Vaga vaga = new Vaga(veiculo);

        vagas.remove(vaga);

        vagaRepository.delete(vaga);
    }
}

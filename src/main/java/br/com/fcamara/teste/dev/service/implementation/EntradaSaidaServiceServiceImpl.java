package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Estabelecimento;
import br.com.fcamara.teste.dev.entity.Vaga;
import br.com.fcamara.teste.dev.entity.Veiculo;
import br.com.fcamara.teste.dev.exception.OperacaoInvalidaException;
import br.com.fcamara.teste.dev.repository.VagaRepository;
import br.com.fcamara.teste.dev.service.definition.EntradaSaidaService;
import org.springframework.stereotype.Service;

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

    @Override
    public Vaga addVehicle(String placa, String cnpj) {
        return null;
    }

    @Override
    public void removeVehicle(String placa, String cnpj) {

    }
}

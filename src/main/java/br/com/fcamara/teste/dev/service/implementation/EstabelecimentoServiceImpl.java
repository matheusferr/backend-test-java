package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.dto.EstabelecimentoDto;
import br.com.fcamara.teste.dev.form.estabelecimento.EstabelecimentoForm;
import br.com.fcamara.teste.dev.form.estabelecimento.EstabelecimentoUpdateForm;
import br.com.fcamara.teste.dev.repository.EstabelecimentoRepository;
import br.com.fcamara.teste.dev.service.definition.EstabelecimentoService;

import java.util.List;

public class EstabelecimentoServiceImpl implements EstabelecimentoService {
    private EnderecoServiceImpl enderecoServiceImpl;
    private CidadeServiceImpl cidadeServiceImpl;
    private EstadoServiceImpl estadoServiceImpl;

    private EstabelecimentoRepository estabelecimentoRepository;

    public EstabelecimentoServiceImpl(EnderecoServiceImpl enderecoServiceImpl, CidadeServiceImpl cidadeServiceImpl, EstadoServiceImpl estadoServiceImpl) {
        this.enderecoServiceImpl = enderecoServiceImpl;
        this.cidadeServiceImpl = cidadeServiceImpl;
        this.estadoServiceImpl = estadoServiceImpl;
    }

    @Override
    public List<EstabelecimentoDto> index() {
        return null;
    }

    @Override
    public EstabelecimentoDto findById(Integer id) {
        return null;
    }

    @Override
    public EstabelecimentoDto create(EstabelecimentoForm estabelecimentoForm) {
        return null;
    }

    @Override
    public EstabelecimentoDto update(Integer id, EstabelecimentoUpdateForm estabelecimentoUpdateForm) {
        return null;
    }

    @Override
    public void destroy(Integer id) {

    }
}

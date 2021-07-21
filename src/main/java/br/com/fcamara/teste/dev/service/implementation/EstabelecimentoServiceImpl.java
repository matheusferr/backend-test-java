package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.*;
import br.com.fcamara.teste.dev.entity.valueObject.CNPJ;
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
    private final CidadeServiceImpl cidadeServiceImpl;
    private final EstadoServiceImpl estadoServiceImpl;
    private final TelefoneServiceImpl telefoneServiceImpl;
    private final EstabelecimentoRepository estabelecimentoRepository;

    public EstabelecimentoServiceImpl(EnderecoServiceImpl enderecoServiceImpl, CidadeServiceImpl cidadeServiceImpl,
                                      EstadoServiceImpl estadoServiceImpl, TelefoneServiceImpl telefoneServiceImpl, EstabelecimentoRepository estabelecimentoRepository) {
        this.enderecoServiceImpl = enderecoServiceImpl;
        this.cidadeServiceImpl = cidadeServiceImpl;
        this.estadoServiceImpl = estadoServiceImpl;
        this.telefoneServiceImpl = telefoneServiceImpl;
        this.estabelecimentoRepository = estabelecimentoRepository;
    }

    private Estabelecimento getEstabelecimento(Integer id) {
        Optional<Estabelecimento> estabelecimento = this.estabelecimentoRepository.findById(id);

        if (estabelecimento.isEmpty()) throw new EntityNotFoundException();

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
    public Estabelecimento create(EstabelecimentoForm estabelecimentoForm) {
        Estabelecimento estabelecimento;

        CNPJ cnpj = new CNPJ(estabelecimentoForm.getCnpj());

        Telefone telefone = new Telefone(estabelecimentoForm.getTelefone());

        Optional<Endereco> enderecoOptional = this.enderecoServiceImpl.findByLogradouroAndNumero(
                estabelecimentoForm.getLogradouro(), estabelecimentoForm.getNumero());

        if (enderecoOptional.isEmpty()) {
            Optional<Cidade> cidadeOptional = this.cidadeServiceImpl.findByNomeCidade(estabelecimentoForm.getCidade());

            if (cidadeOptional.isPresent()) {
                Endereco endereco = this.enderecoServiceImpl.create(estabelecimentoForm.getLogradouro(),
                        estabelecimentoForm.getNumero(), cidadeOptional.get());

                estabelecimento = estabelecimentoForm.toEstabelecimento(endereco, cnpj, telefone);
            } else {
                Estado estado = this.estadoServiceImpl.findByNomeOrCreate(estabelecimentoForm.getEstado());

                Cidade cidade = this.cidadeServiceImpl.create(estabelecimentoForm.getCidade(), estado);

                Endereco endereco = this.enderecoServiceImpl.create(estabelecimentoForm.getLogradouro(),
                        estabelecimentoForm.getNumero(), cidade);

                estabelecimento = estabelecimentoForm.toEstabelecimento(endereco, cnpj, telefone);
            }
        } else estabelecimento = estabelecimentoForm.toEstabelecimento(enderecoOptional.get(), cnpj, telefone);
        this.estabelecimentoRepository.save(estabelecimento);

        return estabelecimento;
    }

    @Override
    public Estabelecimento update(Integer id, EstabelecimentoUpdateForm estabelecimentoUpdateForm) {
        Estabelecimento estabelecimento = this.getEstabelecimento(id);

        estabelecimento.setNomeEstabelecimento(estabelecimentoUpdateForm.getNome());

        return this.estabelecimentoRepository.save(estabelecimento);

    }

    @Override
    public Estabelecimento addPhone(Integer id, EstabelecimentoTelefoneForm estabelecimentoTelefoneForm) {
        return null;
    }

    @Override
    public void removePhone(Integer id, EstabelecimentoTelefoneForm estabelecimentoTelefoneForm) {
    }

    @Override
    public void destroy(Integer id) {
        Estabelecimento estabelecimento = this.getEstabelecimento(id);

        this.estabelecimentoRepository.delete(estabelecimento);
    }
}

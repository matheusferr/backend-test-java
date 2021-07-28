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

        estabelecimento.setNomeEstabelecimento(estabelecimentoUpdateForm.getNome());

        return this.estabelecimentoRepository.save(estabelecimento);

    }

    @Override
    public Estabelecimento addPhone(Integer id, EstabelecimentoTelefoneForm estabelecimentoTelefoneForm) {
        Estabelecimento estabelecimento = this.getEstabelecimento(id);

        Telefone telefone = this.telefoneServiceImpl.findByNumeroOrCreate(estabelecimentoTelefoneForm.getTelefone());

        if(estabelecimento.getTelefones().contains(telefone)) throw new UnsupportedOperationException();

        return this.estabelecimentoRepository.save(estabelecimento);
    }

    @Override
    public void removePhone(Integer id, EstabelecimentoTelefoneForm estabelecimentoTelefoneForm) {
        Estabelecimento estabelecimento = this.getEstabelecimento(id);

        Telefone telefone = this.telefoneServiceImpl.findByNumero(estabelecimentoTelefoneForm.getTelefone());

        if(!estabelecimento.getTelefones().contains(telefone)) throw new UnsupportedOperationException();

        estabelecimento.getTelefones().remove(telefone);

        this.estabelecimentoRepository.save(estabelecimento);
    }

    @Override
    public void destroy(Integer id) {
        Estabelecimento estabelecimento = this.getEstabelecimento(id);

        this.estabelecimentoRepository.delete(estabelecimento);
    }
}

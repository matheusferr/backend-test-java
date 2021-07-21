package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Telefone;
import br.com.fcamara.teste.dev.repository.TelefoneRepository;
import br.com.fcamara.teste.dev.service.definition.TelefoneService;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

public class TelefoneServiceImpl implements TelefoneService {
    private TelefoneRepository telefoneRepository;

    public TelefoneServiceImpl(TelefoneRepository telefoneRepository) {
        this.telefoneRepository = telefoneRepository;
    }

    private Telefone getTelefone(String numeroTelefone) {
        Optional<Telefone> telefone = this.telefoneRepository.findByNumeroTelefone(numeroTelefone);

        if(telefone.isEmpty()) throw new EntityNotFoundException();

        return telefone.get();
    }

    @Override
    public Telefone findByNumero(String numeroTelefone) {
        return this.getTelefone(numeroTelefone);
    }


    @Override
    public Telefone create(String numeroTelefone) {
        Telefone telefone = new Telefone(numeroTelefone);

        return this.telefoneRepository.save(telefone);
    }

    @Override
    public void delete(String numeroTelefone) {
        Telefone telefone = this.getTelefone(numeroTelefone);

        this.telefoneRepository.delete(telefone);
    }
}

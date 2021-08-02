package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Telefone;
import br.com.fcamara.teste.dev.repository.TelefoneRepository;
import br.com.fcamara.teste.dev.service.definition.TelefoneService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class TelefoneServiceImpl implements TelefoneService {
    private final TelefoneRepository telefoneRepository;

    public TelefoneServiceImpl(TelefoneRepository telefoneRepository) {
        this.telefoneRepository = telefoneRepository;
    }

    private Telefone getByTelefone(String numeroTelefone) {
        Optional<Telefone> telefone = this.telefoneRepository.findByNumeroTelefone(numeroTelefone);

        if(telefone.isEmpty()) throw new EntityNotFoundException();

        return telefone.get();
    }

    @Override
    public Telefone findByNumero(String numeroTelefone) {
        return this.getByTelefone(numeroTelefone);
    }

    @Override
    public Telefone findByNumeroOrCreate(String numeroTelefone) {
        Optional<Telefone> telefone = this.telefoneRepository.findByNumeroTelefone(numeroTelefone);

        if(telefone.isEmpty()) return this.telefoneRepository.save(new Telefone(numeroTelefone));

        return telefone.get();
    }

    @Override
    public void delete(String numeroTelefone) {
        Telefone telefone = this.getByTelefone(numeroTelefone);

        this.telefoneRepository.delete(telefone);
    }
}

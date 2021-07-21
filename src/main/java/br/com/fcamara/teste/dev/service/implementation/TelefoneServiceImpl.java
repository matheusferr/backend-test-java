package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.Telefone;
import br.com.fcamara.teste.dev.repository.TelefoneRepository;
import br.com.fcamara.teste.dev.service.definition.TelefoneService;

public class TelefoneServiceImpl implements TelefoneService {
    private TelefoneRepository telefoneRepository;

    public TelefoneServiceImpl(TelefoneRepository telefoneRepository) {
        this.telefoneRepository = telefoneRepository;
    }

    @Override
    public Telefone findByNumero(String numero) {
        return null;
    }

    @Override
    public void delete(String numero) {
    }
}

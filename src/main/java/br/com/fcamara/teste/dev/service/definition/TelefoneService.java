package br.com.fcamara.teste.dev.service.definition;

import br.com.fcamara.teste.dev.entity.Telefone;


public interface TelefoneService {
    Telefone findByNumero(String numeroTelefone);
    Telefone create(String numeroTelefone);
    void delete (String numeroTelefone);
}

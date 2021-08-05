package br.com.fcamara.teste.dev.service.definition;

import br.com.fcamara.teste.dev.entity.Vaga;

public interface EntradaSaidaService {
    Vaga addVehicle(String placa, String cnpj);
    void removeVehicle(String placa, String cnpj);
}

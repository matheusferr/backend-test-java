package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.*;
import br.com.fcamara.teste.dev.entity.enums.VeiculoTipo;
import br.com.fcamara.teste.dev.entity.valueObject.CNPJ;
import br.com.fcamara.teste.dev.entity.valueObject.Placa;
import br.com.fcamara.teste.dev.exception.OperacaoInvalidaException;
import br.com.fcamara.teste.dev.repository.VagaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntradaSaidaServiceServiceImplTest {
    @Mock
    private EstabelecimentoServiceImpl estabelecimentoServiceImpl;

    @Mock
    private VeiculoServiceImpl veiculoServiceImpl;

    @Mock
    private VagaRepository vagaRepository;

    @InjectMocks
    private EntradaSaidaServiceServiceImpl entradaSaidaServiceImpl;

    private Estabelecimento testEstabelecimento;

    private Vaga testVaga;

    private final List<Veiculo> testVeiculos = new ArrayList<>();

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);

        Estado testEstado = new Estado("SÃO PAULO");
        Cidade testCidade = new Cidade("SANTOS", testEstado);
        Endereco testEndereco = new Endereco("AVENIDA CONSELHEIRO NÉBIAS", "1", testCidade);


        this.testEstabelecimento = new Estabelecimento("TEST01", new CNPJ("45857255000103"),
                testEndereco, new Telefone("1234567890"), 1, 1);

        Marca testMarca = new Marca("Fiat");
        Modelo testModelo = new Modelo("Palio", testMarca);
        Cor testCor = new Cor("Vermelho");

        this.testVeiculos.add(new Veiculo(testModelo, testCor, new Placa("BRA1A23"), VeiculoTipo.CARRO));
        this.testVeiculos.add(new Veiculo(testModelo, testCor, new Placa("BRA4A56"),VeiculoTipo.CARRO));

        testVaga = new Vaga(this.testVeiculos.get(0));
    }

    @Test
    void shouldAddAVehicle() {
        Mockito.when(this.veiculoServiceImpl.findByPlaca("BRA1A23")).thenReturn(this.testVeiculos.get(0));
        Mockito.when(this.estabelecimentoServiceImpl.findByCnpj("45857255000103")).thenReturn(
                this.testEstabelecimento
        );

        Mockito.when(this.vagaRepository.save(testVaga)).thenReturn(testVaga);

        Vaga vaga = this.entradaSaidaServiceImpl.addVehicle("BRA1A23", "45857255000103");

        assertEquals(testVaga, vaga);
    }

    @Test
    void shouldNotAddAVehicle() {
        this.testEstabelecimento.getVagas().add(testVaga);

        Mockito.when(this.veiculoServiceImpl.findByPlaca("BRA1A23")).thenReturn(this.testVeiculos.get(0));
        Mockito.when(this.estabelecimentoServiceImpl.findByCnpj("45857255000103")).thenReturn(
                this.testEstabelecimento
        );

        assertThrows(OperacaoInvalidaException.class,
                () -> this.entradaSaidaServiceImpl.addVehicle("BRA1A23", "45857255000103"));
    }

    @Test
    void shouldRemoveAVehicle() {
        this.testEstabelecimento.getVagas().add(testVaga);

        Mockito.when(this.veiculoServiceImpl.findByPlaca("BRA1A23")).thenReturn(this.testVeiculos.get(0));
        Mockito.when(this.estabelecimentoServiceImpl.findByCnpj("45857255000103")).thenReturn(
                this.testEstabelecimento
        );

        this.entradaSaidaServiceImpl.removeVehicle("BRA1A23", "45857255000103");
    }

    @Test
    void shouldNotRemoveAVehicle() {
        Mockito.when(this.veiculoServiceImpl.findByPlaca("BRA1A23")).thenReturn(this.testVeiculos.get(0));
        Mockito.when(this.estabelecimentoServiceImpl.findByCnpj("45857255000103")).thenReturn(
                this.testEstabelecimento
        );


        assertThrows(OperacaoInvalidaException.class,
                () -> this.entradaSaidaServiceImpl.removeVehicle("BRA1A23", "45857255000103"));
    }
}
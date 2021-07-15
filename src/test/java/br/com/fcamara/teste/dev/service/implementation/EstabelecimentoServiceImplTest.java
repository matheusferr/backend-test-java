package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.dto.EstabelecimentoDto;
import br.com.fcamara.teste.dev.entity.*;
import br.com.fcamara.teste.dev.entity.valueObject.CNPJ;
import br.com.fcamara.teste.dev.form.estabelecimento.EstabelecimentoForm;
import br.com.fcamara.teste.dev.form.estabelecimento.EstabelecimentoUpdateForm;
import br.com.fcamara.teste.dev.repository.EstabelecimentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class EstabelecimentoServiceImplTest {

    @Mock
    private EstabelecimentoRepository estabelecimentoRepository;

    @Mock
    private EnderecoServiceImpl enderecoServiceImpl;

    @Mock
    private CidadeServiceImpl cidadeServiceImpl;

    @Mock
    private EstadoServiceImpl estadoServiceImpl;

    @InjectMocks
    private EstabelecimentoServiceImpl estabelecimentoServiceImpl;

    private List<Estabelecimento> testEstabelecimentos = new ArrayList<>();

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);

        Estado testEstado = new Estado("SÃO PAULO");
        Cidade testCidade = new Cidade("SANTOS", testEstado);
        Endereco testEndereco = new Endereco("AVENIDA CONSELHEIRO NÉBIAS", 1, testCidade);

        CNPJ cnpj = new CNPJ("45857255000103");

        Telefone telefone = new Telefone("1234567890");

        Estabelecimento testEstabelecimento = new Estabelecimento("TEST01", cnpj, testEndereco, telefone);

        this.testEstabelecimentos.add(testEstabelecimento);

        testEndereco.setNumero(2);

        telefone.setTelefone("12345678901");

        cnpj.setCnpj("24400188000123");

        testEstabelecimento = new Estabelecimento("TEST02", cnpj, testEndereco, telefone);

        this.testEstabelecimentos.add(testEstabelecimento);
    }

    @Test
    void shoudReturnAListOfEstablishments() {
        Mockito.when(estabelecimentoRepository.findAll()).thenReturn(this.testEstabelecimentos);

        List<EstabelecimentoDto> estabelecimentos = estabelecimentoServiceImpl.index();

        assertEquals(estabelecimentos.size(), 2);
        assertEquals(estabelecimentos, EstabelecimentoDto.convertList(this.testEstabelecimentos));
    }

    @Test
    void shoudReturnAnEstablishmentByItsID() {
        Mockito.when(estabelecimentoRepository.findById(1)).thenReturn(Optional.of(this.testEstabelecimentos.get(0)));

        EstabelecimentoDto estabelecimento = this.estabelecimentoServiceImpl.findById(1);

        assertEquals(estabelecimento, new EstabelecimentoDto(this.testEstabelecimentos.get(0)));
    }

    @Test
    void shouldCreateAnEstablishment() {
        EstabelecimentoForm estabelecimentoForm = new EstabelecimentoForm("TEST02", "24400188000123",
                "AVENIDA CONSELHEIRO NÉBIAS", 2, "SANTOS", "SÃO PAULO", "12345678901");

        Estado estado = new Estado(estabelecimentoForm.getEstado());
        Cidade cidade = new Cidade(estabelecimentoForm.getCidade(), estado);
        Endereco endereco = new Endereco(estabelecimentoForm.getLogradouro(), estabelecimentoForm.getNumero(), cidade);

        Mockito.when(this.enderecoServiceImpl.findByLogradouroAndNumero(
                estabelecimentoForm.getLogradouro(), estabelecimentoForm.getNumero())).thenReturn(Optional.of(endereco));
        Mockito.when(this.estabelecimentoServiceImpl.create(estabelecimentoForm))
                .thenReturn(new EstabelecimentoDto(this.testEstabelecimentos.get(1)));

        EstabelecimentoDto estabelecimento = this.estabelecimentoServiceImpl.create(estabelecimentoForm);

        assertEquals(estabelecimento, this.testEstabelecimentos.get(1));
    }

    @Test
    void shouldUpdateAnEstablishment() {
        EstabelecimentoUpdateForm estabelecimentoUpdateForm = new EstabelecimentoUpdateForm();

        estabelecimentoUpdateForm.setNome("TEST03");

        Mockito.when(this.estabelecimentoServiceImpl.findById(2))
                .thenReturn(new EstabelecimentoDto(this.testEstabelecimentos.get(1)));

        EstabelecimentoDto estabelecimentoAtualizado = this.estabelecimentoServiceImpl
                .update(2, estabelecimentoUpdateForm);

        assertNotEquals(estabelecimentoAtualizado.getNome(), this.testEstabelecimentos.get(1).getNome());
        assertEquals(estabelecimentoAtualizado.getCnpj(), this.testEstabelecimentos.get(1).getCnpj().getCnpjValue());
    }
}
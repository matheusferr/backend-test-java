package br.com.fcamara.teste.dev.service.implementation;

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
        Endereco testEndereco = new Endereco("AVENIDA CONSELHEIRO NÉBIAS", "1", testCidade);

        CNPJ cnpj = new CNPJ("45857255000103");

        Telefone telefone = new Telefone("1234567890");

        Estabelecimento testEstabelecimento = new Estabelecimento("TEST01", cnpj, testEndereco, telefone);

        this.testEstabelecimentos.add(testEstabelecimento);

        testEndereco.setNumero("2");

        telefone.setTelefone("12345678901");

        cnpj.setCnpj("24400188000123");

        testEstabelecimento = new Estabelecimento("TEST02", cnpj, testEndereco, telefone);

        this.testEstabelecimentos.add(testEstabelecimento);
    }

    @Test
    void shoudReturnAListOfEstablishments() {
        Mockito.when(this.estabelecimentoRepository.findAll()).thenReturn(this.testEstabelecimentos);

        List<Estabelecimento> estabelecimentos = this.estabelecimentoServiceImpl.index();

        assertEquals(estabelecimentos.size(), 2);
        assertEquals(estabelecimentos, this.testEstabelecimentos);
    }

    @Test
    void shoudReturnAnEstablishmentByItsID() {
        Mockito.when(this.estabelecimentoRepository.findById(1)).thenReturn(Optional.of(this.testEstabelecimentos.get(0)));

        Estabelecimento estabelecimento = this.estabelecimentoServiceImpl.findById(1);

        assertEquals(estabelecimento, this.testEstabelecimentos.get(0));
    }

    @Test
    void shouldCreateAnEstablishment() {
        EstabelecimentoForm estabelecimentoForm = new EstabelecimentoForm("TEST03", "24400188000123",
                "AVENIDA CONSELHEIRO NÉBIAS", "2", "SANTOS", "SÃO PAULO", "12345678901");
        CNPJ cnpj = new CNPJ(estabelecimentoForm.getCnpj());
        Telefone telefone = new Telefone(estabelecimentoForm.getTelefone());
        Estado estado = new Estado(estabelecimentoForm.getEstado());
        Cidade cidade = new Cidade(estabelecimentoForm.getCidade(), estado);
        Endereco endereco = new Endereco(estabelecimentoForm.getLogradouro(), estabelecimentoForm.getNumero(), cidade);

        Estabelecimento testEstabelecimento = estabelecimentoForm.toEstabelecimento(endereco, cnpj, telefone);

        Mockito.when(this.enderecoServiceImpl.findByLogradouroAndNumero(
                estabelecimentoForm.getLogradouro(), estabelecimentoForm.getNumero())).thenReturn(Optional.empty());

        Mockito.when(this.cidadeServiceImpl.findByNomeCidade(estabelecimentoForm.getCidade()))
                .thenReturn(Optional.empty());

        Mockito.when(this.estadoServiceImpl.findByNomeOrCreate(estabelecimentoForm.getEstado()))
                .thenReturn(estado);

        Mockito.when(this.cidadeServiceImpl.create(estabelecimentoForm.getCidade(), estado)).thenReturn(cidade);

        Mockito.when(this.enderecoServiceImpl.create(estabelecimentoForm.getLogradouro(),
                estabelecimentoForm.getNumero(), cidade)).thenReturn(endereco);

        Mockito.when(this.estabelecimentoRepository.save(estabelecimentoForm.toEstabelecimento(endereco, cnpj, telefone)))
                .thenReturn(testEstabelecimento);

        Estabelecimento estabelecimento = this.estabelecimentoServiceImpl.create(estabelecimentoForm);

        assertEquals(estabelecimento, testEstabelecimento);
    }

    @Test
    void shouldUpdateAnEstablishment() {
        Estabelecimento baseEstabelecimento = this.testEstabelecimentos.get(1);

        Estabelecimento testEstabelecimento = new Estabelecimento(baseEstabelecimento.getNomeEstabelecimento(),
                baseEstabelecimento.getCnpj(), baseEstabelecimento.getEndereco(),
                baseEstabelecimento.getTelefones().get(0));

        EstabelecimentoUpdateForm estabelecimentoUpdateForm = new EstabelecimentoUpdateForm();

        estabelecimentoUpdateForm.setNome("TEST03");

        Mockito.when(this.estabelecimentoRepository.findById(2))
                .thenReturn(Optional.of(testEstabelecimento));

        testEstabelecimento.setNomeEstabelecimento(estabelecimentoUpdateForm.getNome());

        Mockito.when(this.estabelecimentoRepository.save(testEstabelecimento)).thenReturn(testEstabelecimento);

        Estabelecimento estabelecimentoAtualizado = this.estabelecimentoServiceImpl
                .update(2, estabelecimentoUpdateForm);

        assertNotEquals(
                estabelecimentoAtualizado.getNomeEstabelecimento(),
                this.testEstabelecimentos.get(1).getNomeEstabelecimento()
        );
        assertEquals(estabelecimentoAtualizado.getCnpj(), this.testEstabelecimentos.get(1).getCnpj());
    }
}
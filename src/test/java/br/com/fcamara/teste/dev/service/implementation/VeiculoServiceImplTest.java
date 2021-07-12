package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.dto.VeiculoDto;
import br.com.fcamara.teste.dev.entity.Cor;
import br.com.fcamara.teste.dev.entity.Marca;
import br.com.fcamara.teste.dev.entity.Modelo;
import br.com.fcamara.teste.dev.entity.Veiculo;
import br.com.fcamara.teste.dev.entity.enums.VeiculoTipo;
import br.com.fcamara.teste.dev.form.veiculo.VeiculoForm;
import br.com.fcamara.teste.dev.form.veiculo.VeiculoUpdateForm;
import br.com.fcamara.teste.dev.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class VeiculoServiceImplTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @InjectMocks
    private VeiculoServiceImpl veiculoServiceImpl;

    @Mock
    private MarcaServiceImpl marcaServiceImpl;

    @Mock
    private CorServiceImpl corServiceImpl;

    @Mock
    private ModeloServiceImpl modeloServiceImpl;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shoudReturnAListOfVehicles() {
        List<Veiculo> veiculos = Stream.of(
                new Veiculo(
                        new Modelo("Palio", new Marca("Fiat")),
                        new Cor("Vermelho"), "BRA1A123", VeiculoTipo.CARRO
                ),
                new Veiculo(
                        new Modelo("Palio", new Marca("Fiat")),
                        new Cor("Cinza"), "BRA2B234", VeiculoTipo.CARRO
                )
        ).collect(Collectors.toList());

        Mockito.when(veiculoRepository.findAll()).thenReturn(veiculos);

        assertEquals(veiculoServiceImpl.index(), VeiculoDto.convertList(veiculos));

    }

    @Test
    void shouldReturnAVehicleByItsID(){
        Veiculo veiculo = new Veiculo(new Modelo("Palio", new Marca("Fiat")),
                new Cor("Vermelho"), "BRA1A123", VeiculoTipo.CARRO
        );

        Mockito.when(veiculoRepository.findById(1)).thenReturn(Optional.of(veiculo));

        VeiculoDto encontrado = veiculoServiceImpl.findById(1);

        assertEquals(encontrado, new VeiculoDto(veiculo));
    }

    @Test
    void shouldCreateAVehicle(){
        VeiculoForm veiculoForm = new VeiculoForm("Fiat", "Palio", "Vermelho", "BRA1A123", "CARRO");

        Marca marca = new Marca("Fiat");
        Modelo modelo = new Modelo("Palio", marca);
        Cor cor = new Cor("Vermelho");

        Veiculo veiculo = new Veiculo(modelo, cor, "BRA1A123", VeiculoTipo.CARRO);

        Mockito.when(marcaServiceImpl.findByNomeOrCreate(veiculoForm.getMarca())).thenReturn(marca);
        Mockito.when(modeloServiceImpl.findByNomeAndMarcaOrCreate(veiculoForm.getModelo(), marca)).thenReturn(modelo);
        Mockito.when(corServiceImpl.findByNomeOrCreate(veiculoForm.getCor())).thenReturn(cor);
        Mockito.when(veiculoRepository.save(veiculo)).thenReturn(veiculo);

        VeiculoDto novo = veiculoServiceImpl.create(veiculoForm);

        assertEquals(novo, new VeiculoDto(veiculo));
    }

    @Test
    void shouldUpdateAVehicle(){
        Veiculo veiculo = new Veiculo(new Modelo("Palio", new Marca("Fiat")),
                new Cor("Vermelho"), "BRA1A123", VeiculoTipo.CARRO
        );

        VeiculoDto original = new VeiculoDto(veiculo);

        VeiculoUpdateForm veiculoUpdateForm = new VeiculoUpdateForm();

        Cor cor = new Cor("Verde");

        veiculoUpdateForm.setCor(cor.getNomeCor());

        System.out.println(veiculoRepository.findAll());

        Mockito.when(veiculoRepository.save(veiculo)).thenReturn(veiculo);

        assertEquals(veiculoRepository.save(veiculo), veiculo);

        Mockito.when(veiculoRepository.findById(1)).thenReturn(Optional.of(veiculo));

        Mockito.when(corServiceImpl.findByNomeOrCreate(cor.getNomeCor())).thenReturn(cor);

        veiculo.setCor(cor);

        Mockito.when(veiculoRepository.save(veiculo)).thenReturn(veiculo);

        VeiculoDto atualizado = veiculoServiceImpl.update(1, veiculoUpdateForm);

        assertEquals(atualizado.getModelo(), original.getModelo());
        assertEquals(atualizado.getPlaca(), original.getPlaca());
        assertEquals(atualizado.getCor(), cor.getNomeCor());
    }
}
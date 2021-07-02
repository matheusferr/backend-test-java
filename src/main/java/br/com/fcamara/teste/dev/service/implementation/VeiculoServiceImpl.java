package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.dto.VeiculoDto;
import br.com.fcamara.teste.dev.form.VeiculoForm;
import br.com.fcamara.teste.dev.form.VeiculoUpdateForm;
import br.com.fcamara.teste.dev.entity.Cor;
import br.com.fcamara.teste.dev.entity.Marca;
import br.com.fcamara.teste.dev.entity.Modelo;
import br.com.fcamara.teste.dev.entity.Veiculo;
import br.com.fcamara.teste.dev.entity.enums.VeiculoTipo;
import br.com.fcamara.teste.dev.repository.VeiculoRepository;
import br.com.fcamara.teste.dev.service.definition.VeiculoService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculoServiceImpl implements VeiculoService {
    private final VeiculoRepository veiculoRepository;
    private final MarcaServiceImpl marcaServiceImpl;
    private final ModeloServiceImpl modeloServiceImpl;
    private final CorServiceImpl corServiceImpl;

    public VeiculoServiceImpl(VeiculoRepository veiculoRepository, MarcaServiceImpl marcaServiceImpl, ModeloServiceImpl modeloServiceImpl, CorServiceImpl corServiceImpl) {
        this.veiculoRepository = veiculoRepository;
        this.marcaServiceImpl = marcaServiceImpl;
        this.modeloServiceImpl = modeloServiceImpl;
        this.corServiceImpl = corServiceImpl;
    }

    @Override
    public List<VeiculoDto> index() {
        return VeiculoDto.convertList(this.veiculoRepository.findAll());

    }

    @Override
    public VeiculoDto findById(Integer id) {
        Optional<Veiculo> veiculo = this.veiculoRepository.findById(id);

        if(veiculo.isEmpty()) throw new NullPointerException();

        return new VeiculoDto(veiculo.get());
    }

    @Override
    public VeiculoDto create(VeiculoForm veiculoForm){

        Marca marca = marcaServiceImpl.findByNomeOrCreate(veiculoForm.getMarca());

        Modelo modelo = modeloServiceImpl.findByNomeAndMarcaOrCreate(veiculoForm.getModelo(), marca);

        Cor cor = this.corServiceImpl.findByNomeOrCreate(veiculoForm.getCor());

        VeiculoTipo tipo = VeiculoTipo.valueOf(veiculoForm.getTipo());

        Veiculo veiculo = veiculoForm.toVeiculo(modelo, cor, tipo);

        veiculoRepository.save(veiculo);

        return new VeiculoDto(veiculo);
    }

    @Override
    public VeiculoDto update(Integer id, VeiculoUpdateForm veiculoUpdateForm) {
        Optional<Veiculo> veiculo = this.veiculoRepository.findById(id);

        if(veiculo.isEmpty()) throw new EntityNotFoundException();

        Cor cor = corServiceImpl.findByNomeOrCreate(veiculoUpdateForm.getCor());

        Veiculo atualizado = veiculo.get();

        atualizado.setCor(cor);

        veiculoRepository.save(atualizado);

        return new VeiculoDto(atualizado);

    }

    @Override
    public void destroy(Integer id) {
        Optional<Veiculo> veiculo = this.veiculoRepository.findById(id);

        if(veiculo.isEmpty()) throw new EntityNotFoundException();

        else this.veiculoRepository.delete(veiculo.get());
    }
}

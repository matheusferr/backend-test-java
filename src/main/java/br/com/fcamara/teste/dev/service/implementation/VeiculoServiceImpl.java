package br.com.fcamara.teste.dev.service.implementation;

import br.com.fcamara.teste.dev.entity.valueObject.Placa;
import br.com.fcamara.teste.dev.form.veiculo.VeiculoForm;
import br.com.fcamara.teste.dev.form.veiculo.VeiculoUpdateForm;
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
	private final MarcaServiceImpl marcaServiceImpl;
	private final ModeloServiceImpl modeloServiceImpl;
	private final CorServiceImpl corServiceImpl;
	private final VeiculoRepository veiculoRepository;

	public VeiculoServiceImpl(MarcaServiceImpl marcaServiceImpl, ModeloServiceImpl modeloServiceImpl,
	                          CorServiceImpl corServiceImpl, VeiculoRepository veiculoRepository) {
		this.marcaServiceImpl = marcaServiceImpl;
		this.modeloServiceImpl = modeloServiceImpl;
		this.corServiceImpl = corServiceImpl;
		this.veiculoRepository = veiculoRepository;
	}

	private Veiculo getVeiculo(Integer id) {
		Optional<Veiculo> veiculo = this.veiculoRepository.findById(id);

		if(veiculo.isEmpty()) throw new EntityNotFoundException();

		return veiculo.get();
	}

	@Override
	public List<Veiculo> index() {
		return this.veiculoRepository.findAll();
	}

	@Override
	public Veiculo findById(Integer id) {
		return this.getVeiculo(id);
	}

	@Override
	public Veiculo findByPlaca(String placa) {
		Optional<Veiculo> veiculo = this.veiculoRepository.findByPlaca(new Placa(placa));

		if(veiculo.isEmpty()) throw new EntityNotFoundException();

		return veiculo.get();
	}

	@Override
	public Veiculo create(VeiculoForm veiculoForm) {
		Veiculo veiculo;

		VeiculoTipo tipo = VeiculoTipo.valueOf(veiculoForm.getTipo());

		Cor cor = this.corServiceImpl.findByNomeOrCreate(veiculoForm.getCor());

		Optional<Modelo> modeloOptional = this.modeloServiceImpl.findByNomeModelo(veiculoForm.getModelo());

		if(modeloOptional.isPresent()) {
			veiculo = veiculoForm.toVeiculo(modeloOptional.get(), cor, tipo);
		}
		else {
			Marca marca = this.marcaServiceImpl.findByNomeOrCreate(veiculoForm.getMarca());

			Modelo modelo = this.modeloServiceImpl.create(veiculoForm.getModelo(), marca);

			veiculo = veiculoForm.toVeiculo(modelo, cor, tipo);
		}

		return this.veiculoRepository.save(veiculo);
	}

	@Override
	public Veiculo update(Integer id, VeiculoUpdateForm veiculoUpdateForm) {
		Veiculo veiculo = this.getVeiculo(id);

		Cor cor = corServiceImpl.findByNomeOrCreate(veiculoUpdateForm.getCor());

		veiculo.setCor(cor);

		return veiculoRepository.save(veiculo);
	}

	@Override
	public void destroy(Integer id) {
		Veiculo veiculo = this.getVeiculo(id);

		this.veiculoRepository.delete(veiculo);
	}
}

package br.com.fcamara.teste.dev.form.estabelecimento;

import br.com.fcamara.teste.dev.entity.Endereco;
import br.com.fcamara.teste.dev.entity.Estabelecimento;
import br.com.fcamara.teste.dev.entity.Telefone;
import br.com.fcamara.teste.dev.entity.Vagas;
import br.com.fcamara.teste.dev.entity.valueObject.CNPJ;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
public class EstabelecimentoForm {
    @NotNull @NotEmpty
    private String nome;

    @NotNull @NotEmpty @Pattern(regexp = "^\\d{14}$")
    private String cnpj;

    @NotNull @NotEmpty
    private String logradouro;

    @NotNull @NotEmpty @Pattern(regexp = "^s/n|\\d$", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String numero;

    @NotNull @NotEmpty
    private String cidade;

    @NotNull @NotEmpty
    private String estado;

    @NotNull @NotEmpty @Pattern(regexp = "^\\d{11}|\\d{10}$")
    private String telefone;

    @NotNull @Min(1)
    private Integer vagasCarro;

    @NotNull @Min(1)
    private Integer vagasMoto;

    public Estabelecimento toEstabelecimento(Endereco endereco, CNPJ cnpj, Telefone telefone, Vagas vagas){
        return new Estabelecimento(this.nome, cnpj, endereco, telefone, vagas);
    }
}

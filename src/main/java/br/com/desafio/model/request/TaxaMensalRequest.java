package br.com.desafio.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaxaMensalRequest implements Serializable {

    @JsonProperty("Mes")
    @Pattern(regexp = "[A-Z]{1}[a-z]{2}-[0-9]{4}", message = "{mes.not.valid}")
    public String mes;
    @JsonProperty("Modalidade")
    @NotBlank(message = "{modalidade.not.blank}")
    public String modalidade;

    @JsonProperty("Posicao")
    @NotNull(message = "{posicao.not.null}")
    @Range(min = 1, max = 15, message = "{posicao.not.valid}!")
    public Integer posicao;

    @JsonProperty("InstituicaoFinanceira")
    @NotBlank(message = "{instituicaoFinanceira.not.blank}")
    public String instituicaoFinanceira;

    @JsonProperty("TaxaJurosAoMes")
    @NotNull(message = "{taxaJurosAoMes.not.null}")
    @Digits(integer = 2, fraction = 2)
    public Double taxaJurosAoMes;

    @JsonProperty("TaxaJurosAoAno")
    @NotNull(message = "{taxaJurosAoAno.not.null}")
    @Digits(integer = 2, fraction = 2)
    public Double taxaJurosAoAno;

    @JsonProperty("cnpj8")
    @Pattern(regexp = "[0-9]{8}", message = "{cnpj8.not.valid}")
    public String cnpj8;

    @JsonProperty("anoMes")
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}", message = "{anoMes.not.valid}")
    public String anoMes;


}

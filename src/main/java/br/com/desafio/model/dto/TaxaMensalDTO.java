package br.com.desafio.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaxaMensalDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;
    @JsonProperty("Mes")
    public String mes;
    @JsonProperty("Modalidade")
    public String modalidade;
    @JsonProperty("Posicao")
    public Integer posicao;
    @JsonProperty("InstituicaoFinanceira")
    public String instituicaoFinanceira;
    @JsonProperty("TaxaJurosAoMes")
    public Double taxaJurosAoMes;
    @JsonProperty("TaxaJurosAoAno")
    public Double taxaJurosAoAno;
    @JsonProperty("cnpj8")
    public String cnpj8;
    @JsonProperty("anoMes")
    public String anoMes;

}

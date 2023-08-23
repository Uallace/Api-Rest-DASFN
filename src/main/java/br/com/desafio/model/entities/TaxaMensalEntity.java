package br.com.desafio.model.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Entity
@Builder
@Table(name = "Taxa_Mensal")
@NoArgsConstructor
@AllArgsConstructor
public class TaxaMensalEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @NotBlank(message = "O campo cnpj8 nao pode ser vazio!")
    public String cnpj8;

    @NotBlank(message = "O campo anoMes nao pode ser vazio!")
    public String anoMes;
}

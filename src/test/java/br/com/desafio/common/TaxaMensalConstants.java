package br.com.desafio.common;

import br.com.desafio.model.dto.TaxaMensalDTO;
import br.com.desafio.model.entities.TaxaMensalEntity;
import br.com.desafio.model.request.TaxaMensalRequest;
import br.com.desafio.model.response.TaxaMensalResponse;

import java.util.ArrayList;
import java.util.List;

public class TaxaMensalConstants {

    public static final TaxaMensalEntity TAXA_MENSAL = TaxaMensalEntity.builder()
            .id(1L)
            .mes("Jan-2023")
            .modalidade("Financiamento")
            .posicao(2)
            .instituicaoFinanceira("APE POUPEX")
            .taxaJurosAoMes(0.93)
            .taxaJurosAoAno(10.09)
            .cnpj8("00655522")
            .anoMes("2023-01").build();

    public static final TaxaMensalDTO TAXA_MENSAL_DTO = TaxaMensalDTO.builder()
            .id(1L)
            .mes("Jan-2023")
            .modalidade("Financiamento")
            .posicao(2)
            .instituicaoFinanceira("APE POUPEX")
            .taxaJurosAoMes(0.93)
            .taxaJurosAoAno(10.09)
            .cnpj8("00655522")
            .anoMes("2023-01").build();

    public static final TaxaMensalResponse TAXA_MENSAL_RESPONSE = TaxaMensalResponse.builder()
            .id(1L)
            .mes("Jan-2023")
            .modalidade("Financiamento")
            .posicao(2)
            .instituicaoFinanceira("APE POUPEX")
            .taxaJurosAoMes(0.93)
            .taxaJurosAoAno(10.09)
            .cnpj8("00655522")
            .anoMes("2023-01").build();

    public static final TaxaMensalRequest TAXA_MENSAL_REQUEST = TaxaMensalRequest.builder()
            .mes("Jan-2023")
            .modalidade("Financiamento")
            .posicao(2)
            .instituicaoFinanceira("APE POUPEX")
            .taxaJurosAoMes(0.93)
            .taxaJurosAoAno(10.09)
            .cnpj8("00655522")
            .anoMes("2023-01").build();


    public static final TaxaMensalEntity TAXA_MENSAL_INVALIDA = TaxaMensalEntity.builder()
            .mes("")
            .modalidade("")
            .posicao(null)
            .instituicaoFinanceira("")
            .taxaJurosAoMes(null)
            .taxaJurosAoAno(null)
            .cnpj8("")
            .anoMes("").build();

public static final TaxaMensalDTO TAXA_MENSAL_DTO_INVALIDA = TaxaMensalDTO.builder()
            .mes("")
            .modalidade("")
            .posicao(null)
            .instituicaoFinanceira("")
            .taxaJurosAoMes(null)
            .taxaJurosAoAno(null)
            .cnpj8("")
            .anoMes("").build();

    public static final String BASE_URL = "https://olinda.bcb.gov.br/olinda/servico/taxaJuros/versao/v2/odata/TaxasJurosMensalPorMes?$top=100&$format=json";

     public static final String RESPONSE =  "{\"@odata.context\": \"https://was-p.bcnet.bcb.gov.br/olinda/servico/taxaJuros/versao/v2/odata$metadata#TaxasJurosMensalPorMes\",\n" +
            "   \"value\": [{\n" +
            "   \"Mes\": \"Jan-2023\",\n" +
            "   \"Modalidade\": \"FINANCIAMENTO IMOBILIÁRIO COM TAXAS REGULADAS - PRÉ-FIXADO\",\n" +
            "   \"Posicao\": 1,\n" +
            "   \"InstituicaoFinanceira\": \"BCO DO BRASIL S.A.\",\n" +
            "   \"TaxaJurosAoMes\": 0.0,\n" +
            "   \"TaxaJurosAoAno\": 0.0,\n" +
            "   \"cnpj8\": \"00000000\",\n" +
            "   \"anoMes\": \"2023-01\"}]}";

     public static final List<TaxaMensalEntity> listTaxaMensal()
     {
         List<TaxaMensalEntity> taxas = new ArrayList<>();
         taxas.add(TaxaMensalEntity.builder()
                 .id(1L)
                 .mes("Jan-2023")
                 .modalidade("Financiamento")
                 .posicao(2)
                 .instituicaoFinanceira("APE POUPEX")
                 .taxaJurosAoMes(0.93)
                 .taxaJurosAoAno(10.09)
                 .cnpj8("00655522")
                 .anoMes("2023-01").build());

         taxas.add(TaxaMensalEntity.builder()
                 .id(2L)
                 .mes("Jan-2023")
                 .modalidade("FINANCIAMENTO IMOBILIÁRIO COM TAXAS DE MERCADO - PÓS-FIXADO REFERENCIADO EM IPCA")
                 .posicao(1)
                 .instituicaoFinanceira("BCO RODOBENS S.A.")
                 .taxaJurosAoMes(0.0)
                 .taxaJurosAoAno(0.0)
                 .cnpj8("33603457")
                 .anoMes("2023-01").build());

         return taxas;
     }

    public static final List<TaxaMensalResponse> listTaxaMensalResponse()
    {
        List<TaxaMensalResponse> responseList = new ArrayList<>();
        responseList.add(TaxaMensalResponse.builder()
                .id(1L)
                .mes("Jan-2023")
                .modalidade("Financiamento")
                .posicao(2)
                .instituicaoFinanceira("APE POUPEX")
                .taxaJurosAoMes(0.93)
                .taxaJurosAoAno(10.09)
                .cnpj8("00655522")
                .anoMes("2023-01").build());

        responseList.add(TaxaMensalResponse.builder()
                .id(2L)
                .mes("Jan-2023")
                .modalidade("FINANCIAMENTO IMOBILIÁRIO COM TAXAS DE MERCADO - PÓS-FIXADO REFERENCIADO EM IPCA")
                .posicao(1)
                .instituicaoFinanceira("BCO RODOBENS S.A.")
                .taxaJurosAoMes(0.0)
                .taxaJurosAoAno(0.0)
                .cnpj8("33603457")
                .anoMes("2023-01").build());

        return responseList;
    }

    public static final List<TaxaMensalDTO> listTaxaMensalDTO()
    {
        List<TaxaMensalDTO> dtoList = new ArrayList<>();
        dtoList.add(TaxaMensalDTO.builder()
                .id(1L)
                .mes("Jan-2023")
                .modalidade("Financiamento")
                .posicao(2)
                .instituicaoFinanceira("APE POUPEX")
                .taxaJurosAoMes(0.93)
                .taxaJurosAoAno(10.09)
                .cnpj8("00655522")
                .anoMes("2023-01").build());

        dtoList.add(TaxaMensalDTO.builder()
                .id(2L)
                .mes("Jan-2023")
                .modalidade("FINANCIAMENTO IMOBILIÁRIO COM TAXAS DE MERCADO - PÓS-FIXADO REFERENCIADO EM IPCA")
                .posicao(1)
                .instituicaoFinanceira("BCO RODOBENS S.A.")
                .taxaJurosAoMes(0.0)
                .taxaJurosAoAno(0.0)
                .cnpj8("33603457")
                .anoMes("2023-01").build());

        return dtoList;
    }

    public static final List<TaxaMensalRequest> listTaxaMensalRequest()
    {
        List<TaxaMensalRequest> requestList = new ArrayList<>();
        requestList.add(TaxaMensalRequest.builder()
                .mes("Jan-2023")
                .modalidade("Financiamento")
                .posicao(2)
                .instituicaoFinanceira("APE POUPEX")
                .taxaJurosAoMes(0.93)
                .taxaJurosAoAno(10.09)
                .cnpj8("00655522")
                .anoMes("2023-01").build());

        requestList.add(TaxaMensalRequest.builder()
                .mes("Jan-2023")
                .modalidade("FINANCIAMENTO IMOBILIÁRIO COM TAXAS DE MERCADO - PÓS-FIXADO REFERENCIADO EM IPCA")
                .posicao(1)
                .instituicaoFinanceira("BCO RODOBENS S.A.")
                .taxaJurosAoMes(0.0)
                .taxaJurosAoAno(0.0)
                .cnpj8("33603457")
                .anoMes("2023-01").build());

        return requestList;
    }

}

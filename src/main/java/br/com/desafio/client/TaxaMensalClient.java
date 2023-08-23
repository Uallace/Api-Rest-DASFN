package br.com.desafio.client;

import br.com.desafio.model.response.Root;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "TaxaMensal", url = "https://olinda.bcb.gov.br/olinda/servico/taxaJuros/versao/v2/odata/TaxasJurosMensalPorMes?$top=100&$format=json")
public interface TaxaMensalClient {

    @GetMapping()
    Root getAllApi();

}

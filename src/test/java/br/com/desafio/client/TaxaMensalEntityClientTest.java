package br.com.desafio.client;

import br.com.desafio.model.response.TaxaMensalResponse;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.mock.HttpMethod;
import feign.mock.MockClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.support.SpringMvcContract;

import java.util.List;

import static br.com.desafio.common.TaxaMensalConstants.BASE_URL;
import static br.com.desafio.common.TaxaMensalConstants.RESPONSE;
import static org.assertj.core.api.Assertions.assertThat;

public class TaxaMensalEntityClientTest {

    @Autowired
    private TaxaMensalClient client;

    private void builderFeignClient(MockClient mockClient){
        client = Feign.builder()
                .client(mockClient)
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .contract(new SpringMvcContract())
                .target(TaxaMensalClient.class, BASE_URL);
    }

    @Test
    public void deveRetornarListaParaOnbording(){

        this.builderFeignClient(new MockClient().ok(
                HttpMethod.GET,
                BASE_URL,
                RESPONSE
        ));

        List<TaxaMensalResponse> responseList = client.getAllApi().value;

        assertThat(responseList).isNotEmpty();
        assertThat(responseList).hasSize(1);
    }

}

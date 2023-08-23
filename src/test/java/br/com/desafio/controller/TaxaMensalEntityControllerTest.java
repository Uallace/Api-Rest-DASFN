package br.com.desafio.controller;

import br.com.desafio.model.entities.TaxaMensalEntity;
import br.com.desafio.model.response.TaxaMensalResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.desafio.service.TaxaMensalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.Collections;
import java.util.List;


import static br.com.desafio.common.TaxaMensalConstants.*;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaxaMensalController.class)
public class TaxaMensalEntityControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TaxaMensalService taxaMensalService;

    @Test
    public void deveSalvarDaApiExternaComDadosValidosRetornandoOk() throws Exception{

        when(taxaMensalService.saveApi(listTaxaMensalDTO())).thenReturn(listTaxaMensalDTO());

         mockMvc.perform(post("/api/onbording")
                        .content(objectMapper.writeValueAsString(listTaxaMensalRequest()))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$", hasSize(2)));

        verify(taxaMensalService, times(1)).saveApi(listTaxaMensalDTO());
    }

    @Test
    public void deveRetornarExceptionParaSalvarApiExternaComDadosInvalidos() throws Exception{

        List<TaxaMensalEntity> listaVazia = null;

        mockMvc.perform(post("/api/onbording").content(objectMapper.writeValueAsString(listaVazia))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest());

        mockMvc.perform(post("/api/onbording").content(objectMapper.writeValueAsString(TAXA_MENSAL_INVALIDA))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveRetornarListarTodasAsTaxas() throws Exception{
        when(taxaMensalService.getAllTaxas()).thenReturn(listTaxaMensalDTO());
        mockMvc.perform(get("/api/taxas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        verify(taxaMensalService, times(1)).getAllTaxas();
    }

    @Test
    public void deveRetornarListaVaziaTaxaMensal() throws Exception{
        when(taxaMensalService.getAllTaxas()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/taxas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        verify(taxaMensalService, times(1)).getAllTaxas();

    }


    @Test
    public void deveCriarTaxaMensalValidaRetornandoOk() throws Exception{

       when(taxaMensalService.saveEntity(TAXA_MENSAL_DTO)).thenReturn(TAXA_MENSAL_DTO);

          mockMvc.perform(post("/api/salvar")
                        .content(objectMapper.writeValueAsString(TAXA_MENSAL_REQUEST))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.Mes").value(TAXA_MENSAL_REQUEST.getMes()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.Modalidade").value(TAXA_MENSAL_REQUEST.getModalidade()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.Posicao").value(TAXA_MENSAL_REQUEST.getPosicao()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.InstituicaoFinanceira").value(TAXA_MENSAL_REQUEST.getInstituicaoFinanceira()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.TaxaJurosAoMes").value(TAXA_MENSAL_REQUEST.getTaxaJurosAoMes()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.TaxaJurosAoAno").value(TAXA_MENSAL_REQUEST.getTaxaJurosAoAno()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.cnpj8").value(TAXA_MENSAL_REQUEST.getCnpj8()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.anoMes").value(TAXA_MENSAL_REQUEST.getAnoMes()));

         verify(taxaMensalService,times(1)).saveEntity(TAXA_MENSAL_DTO);

    }

    @Test
    public void deveRetornarBadRequestParaTaxaMensalComDadosInvalidos() throws Exception{

       TaxaMensalEntity taxaVazia = new TaxaMensalEntity();


        mockMvc.perform(post("/api/salvar")
                        .content(objectMapper.writeValueAsString(taxaVazia))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(post("/api/salvar")
                        .content(objectMapper.writeValueAsString(TAXA_MENSAL_INVALIDA))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void deveRetornarTaxaMensalcomIdExistente() throws Exception{

        when(taxaMensalService.getById(1L)).thenReturn(TAXA_MENSAL_DTO);

        mockMvc.perform(get("/api/taxa/1"))
                        .andExpect(status().isOk());

        verify(taxaMensalService, times(1)).getById(1L);

    }

    @Test
    public void deveRetornarExceptionParaTaxaMensalcomIdInexistente() throws Exception {

        doThrow(new EmptyResultDataAccessException(1)).when(taxaMensalService).delete(1L);

        mockMvc.perform(get("/api/taxa/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deveRetornarTaxaMensalPorAnoMesExistente() throws Exception{

        String anoMes = "2023-01";

        when(taxaMensalService.getByAnoMes(TAXA_MENSAL.getAnoMes())).thenReturn(listTaxaMensalDTO());

        mockMvc.perform(get("/api/anomes/" + anoMes).content(objectMapper.writeValueAsString(listTaxaMensalDTO()))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    public void deveDeletarTaxaMensalcomIdExistente() throws Exception{

        Long id = 1L;

        mockMvc.perform(delete("/api/taxa/" + id))
                .andExpect(status().isNoContent());

        verify(taxaMensalService, times(1)).delete(id);
    }

    @Test
    public void deveRetornarExceptionAoDeletarTaxaMensalcomIdInexistente() throws Exception{

        Long id = 1L;

        doThrow(new EmptyResultDataAccessException(1)).when(taxaMensalService).delete(id);

        mockMvc.perform(delete("/api/taxa/" + id))
                .andExpect(status().isNotFound());

        verify(taxaMensalService, times(1)).delete(id);
    }

    @Test
    public void deveAtualizarTaxaMensal() throws Exception {

        Long id = 1L;

        when(taxaMensalService.update(TAXA_MENSAL_DTO,id)).thenReturn(TAXA_MENSAL_DTO);

         mockMvc.perform(put("/api/taxa/" + id)
                        .content(objectMapper.writeValueAsString(TAXA_MENSAL_REQUEST))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.Mes").value(TAXA_MENSAL_REQUEST.getMes()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.Modalidade").value(TAXA_MENSAL_REQUEST.getModalidade()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.Posicao").value(TAXA_MENSAL_REQUEST.getPosicao()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.InstituicaoFinanceira").value(TAXA_MENSAL_REQUEST.getInstituicaoFinanceira()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.TaxaJurosAoMes").value(TAXA_MENSAL_REQUEST.getTaxaJurosAoMes()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.TaxaJurosAoAno").value(TAXA_MENSAL_REQUEST.getTaxaJurosAoAno()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.cnpj8").value(TAXA_MENSAL_REQUEST.getCnpj8()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.anoMes").value(TAXA_MENSAL_REQUEST.getAnoMes()));

        verify(taxaMensalService,times(1)).update(TAXA_MENSAL_DTO,id);

    }

    @Test
    public void deveRetornarTodasAsTaxasPorPaginacao() throws Exception{

        PageRequest paginacao = PageRequest.of(1, 20);
        List<TaxaMensalResponse> taxas = listTaxaMensalResponse();
        Page<TaxaMensalResponse> pages = new PageImpl<>(taxas, paginacao, taxas.size());

        mockMvc.perform(get("/api/pages?page=1&size=20").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}

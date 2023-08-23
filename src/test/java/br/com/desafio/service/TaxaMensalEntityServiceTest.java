package br.com.desafio.service;

import br.com.desafio.exception.BadRequestException;
import br.com.desafio.mapper.TaxaMensalMapper;
import br.com.desafio.model.entities.TaxaMensalEntity;
import br.com.desafio.model.response.TaxaMensalResponse;
import br.com.desafio.repository.TaxaMensalRepository;
import br.com.desafio.model.dto.TaxaMensalDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;


import java.util.Collections;
import java.util.List;
import java.util.Optional;


import static br.com.desafio.common.TaxaMensalConstants.*;
import static br.com.desafio.common.TaxaMensalConstants.listTaxaMensal;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class TaxaMensalEntityServiceTest {

    @InjectMocks
    private TaxaMensalService taxaMensalService;

    @Mock
    private TaxaMensalRepository repo;

    private TaxaMensalMapper mapper = Mappers.getMapper(TaxaMensalMapper.class);


    @Test
    public void deveSalvarEntidadesComDadosValidosdaApiExterna(){

        when(repo.saveAll(listTaxaMensal())).thenReturn(listTaxaMensal());

        List<TaxaMensalDTO> dto = mapper.entityListToDtoList(repo.saveAll(listTaxaMensal()));
        List<TaxaMensalResponse> responseList = mapper.dtoListToResponseList(dto);

        assertThat(responseList).isNotEmpty();
        assertThat(responseList).hasSize(2);
        assertThat(responseList).isEqualTo(listTaxaMensalResponse());
        verify(repo, times(1)).saveAll(listTaxaMensal());
    }

    @Test
    public void deveRetornarExceptionAoTentarSalvarListComDadosInvalidos(){

        when(repo.saveAll(listTaxaMensal())).thenThrow(BadRequestException.class);
        assertThatThrownBy(() -> taxaMensalService.saveApi(listTaxaMensalDTO())).isInstanceOf(BadRequestException.class);

    }


    @Test
    public void deveSalvarEntidadeComDadosValidosRetornandoTaxaMensal(){

        when(repo.save(TAXA_MENSAL)).thenReturn(TAXA_MENSAL);

        TaxaMensalDTO dto = mapper.entityToDto(repo.save(TAXA_MENSAL));
        TaxaMensalResponse response = mapper.dtoToResponse(dto);

        assertThat(response).isEqualTo(TAXA_MENSAL_RESPONSE);
        verify(repo, times(1)).save(TAXA_MENSAL);

    }

    @Test
    public void deveRetornarExceptionAoTentarSalvarComDadosInvalidosDaApiExterna(){

        when(repo.saveAll(Collections.emptyList())).thenThrow(RuntimeException.class);
        assertThatThrownBy(() -> taxaMensalService.saveApi(Collections.emptyList()));
    }


    @Test
    public void deveRetornarExceptionAoTentarSalvarComDadosInvalidos(){

        when(repo.save(TAXA_MENSAL_INVALIDA)).thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> taxaMensalService.saveEntity(TAXA_MENSAL_DTO_INVALIDA)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void deveRetornarTaxaMensalExistentePorId(){

        when(repo.findById(1L)).thenReturn(Optional.of(TAXA_MENSAL));

        TaxaMensalDTO dto = taxaMensalService.getById(1L);

        assertThat(dto).isNotNull();
        assertThat(dto).isEqualTo(TAXA_MENSAL_DTO);

        verify(repo,times(1)).findById(1L);

    }

    @Test
    public void deveRetornarExceptionParaTaxaMensalNaoEncontradaPorId(){

        when(repo.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> taxaMensalService.getById(anyLong())).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void deveRetornarTaxasExistentePorAnoMes(){

        String anoMes = "2023-01";

        when(repo.findByanoMes(anoMes)).thenReturn(listTaxaMensal());

        List<TaxaMensalDTO> listDto = mapper.entityListToDtoList(repo.findByanoMes(anoMes));
        List<TaxaMensalResponse> responseList = mapper.dtoListToResponseList(listDto);


        assertThat(responseList).isNotEmpty();
        assertThat(responseList).isEqualTo(listTaxaMensalResponse());
        verify(repo, times(1)).findByanoMes(anoMes);
    }

    @Test
    public void deveRetornarExcetionParaTaxaMensalPorAnoMesNaoEncontrada(){

        when(repo.findByanoMes(anyString())).thenReturn(Collections.emptyList());

        assertThatThrownBy(() -> taxaMensalService.getByAnoMes(anyString())).isInstanceOf(BadRequestException.class);

    }

    @Test
    public void deveRetornarTodasAsTaxasMensaisExistentes(){

        when(repo.findAll()).thenReturn(listTaxaMensal());

        List<TaxaMensalDTO> dtoList = taxaMensalService.getAllTaxas();

        assertThat(dtoList).isNotEmpty();
        assertThat(dtoList).isEqualTo(listTaxaMensalDTO());

        verify(repo, times(1)).findAll();

    }

    @Test
    public void deveRetornarExceptionParaTaxaMensalVazia(){

        when(repo.findAll()).thenReturn(Collections.emptyList());

       assertThatThrownBy(() -> taxaMensalService.getAllTaxas()).isInstanceOf(BadRequestException.class);


    }

    @Test
    public void deveDeletarTaxaMensalComIdExistente(){

        when(repo.findById(1L)).thenReturn(Optional.of(TAXA_MENSAL));

        assertThatCode(() -> taxaMensalService.delete(TAXA_MENSAL_DTO.getId())).doesNotThrowAnyException();

    }

    @Test
    public void deveRetornarExceptionParaDeletarTaxaMensalInexistente(){

        assertThatThrownBy(() -> taxaMensalService.delete(1L)).isInstanceOf(BadRequestException.class);

    }

    @Test
    public void deveAtualizarTaxaMensalExistente(){

        when(repo.findById(1L)).thenReturn(Optional.of(TAXA_MENSAL));
        when(repo.save(TAXA_MENSAL)).thenReturn(TAXA_MENSAL);

        TaxaMensalDTO dto = taxaMensalService.update(TAXA_MENSAL_DTO,1L);

        assertThat(dto).isNotNull();
        assertThat(dto).isEqualTo(TAXA_MENSAL_DTO);

        verify(repo, times(1)).findById(anyLong());
        verify(repo, times(1)).save(any(TaxaMensalEntity.class));

    }

    @Test
    public void deveRetornarExceptionAoTentarAtualizarTaxaMensal(){

        when(repo.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> taxaMensalService.update(TAXA_MENSAL_DTO, 103L)).isInstanceOf(BadRequestException.class);

    }

     @Test
    public void deveRetornarPaginacaoTaxaMensal(){

         PageImpl<TaxaMensalEntity> taxaPage = new PageImpl<>(List.of(TAXA_MENSAL));
         when(repo.findAll((any(PageRequest.class))))
                 .thenReturn(taxaPage);

         String expectativaAnoMes = TAXA_MENSAL.getAnoMes();

         Page<TaxaMensalResponse> page = taxaMensalService.getPages(PageRequest.of(1,1));

         assertThat(page).isNotNull();
         assertThat(page.toList()).isNotEmpty().hasSize(1);
         assertThat(page.toList().get(0).getAnoMes()).isEqualTo(expectativaAnoMes);

         verify(repo, times(1)).findAll(any(PageRequest.class));
     }
}

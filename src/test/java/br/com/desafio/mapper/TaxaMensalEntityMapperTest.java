package br.com.desafio.mapper;

import br.com.desafio.model.entities.TaxaMensalEntity;
import br.com.desafio.model.dto.TaxaMensalDTO;
import br.com.desafio.model.request.TaxaMensalRequest;
import br.com.desafio.model.response.TaxaMensalResponse;
import br.com.desafio.service.TaxaMensalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static br.com.desafio.common.TaxaMensalConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class TaxaMensalEntityMapperTest {

    @InjectMocks
    private TaxaMensalService taxaMensalService;

    private TaxaMensalMapper mapper = Mappers.getMapper(TaxaMensalMapper.class);

    @Test
    public void deveConverterEntityParaDto(){

        TaxaMensalDTO dto = mapper.entityToDto(TAXA_MENSAL);

        assertThat(dto).isNotNull();
        assertThat(TAXA_MENSAL.getId()).isEqualTo(dto.getId());
        assertThat(TAXA_MENSAL.getMes()).isEqualTo(dto.getMes());
        assertThat(TAXA_MENSAL.getModalidade()).isEqualTo(dto.getModalidade());
        assertThat(TAXA_MENSAL.getPosicao()).isEqualTo(dto.getPosicao());
        assertThat(TAXA_MENSAL.getInstituicaoFinanceira()).isEqualTo(dto.getInstituicaoFinanceira());
        assertThat(TAXA_MENSAL.getTaxaJurosAoMes()).isEqualTo(dto.getTaxaJurosAoMes());
        assertThat(TAXA_MENSAL.getTaxaJurosAoAno()).isEqualTo(dto.getTaxaJurosAoAno());
        assertThat(TAXA_MENSAL.getCnpj8()).isEqualTo(dto.getCnpj8());
        assertThat(TAXA_MENSAL.getAnoMes()).isEqualTo(dto.getAnoMes());

    }

    @Test
    public void deveConverterDtoParaEntity(){

        TaxaMensalEntity entity = mapper.dtoToEntity(TAXA_MENSAL_DTO);

        assertThat(entity).isNotNull();
        assertThat(TAXA_MENSAL_DTO.getId()).isEqualTo(entity.getId());
        assertThat(TAXA_MENSAL_DTO.getMes()).isEqualTo(entity.getMes());
        assertThat(TAXA_MENSAL_DTO.getModalidade()).isEqualTo(entity.getModalidade());
        assertThat(TAXA_MENSAL_DTO.getPosicao()).isEqualTo(entity.getPosicao());
        assertThat(TAXA_MENSAL_DTO.getInstituicaoFinanceira()).isEqualTo(entity.getInstituicaoFinanceira());
        assertThat(TAXA_MENSAL_DTO.getTaxaJurosAoMes()).isEqualTo(entity.getTaxaJurosAoMes());
        assertThat(TAXA_MENSAL_DTO.getTaxaJurosAoAno()).isEqualTo(entity.getTaxaJurosAoAno());
        assertThat(TAXA_MENSAL_DTO.getCnpj8()).isEqualTo(entity.getCnpj8());
        assertThat(TAXA_MENSAL_DTO.getAnoMes()).isEqualTo(entity.getAnoMes());
    }

    @Test
    public void deveConverterDtoParaResponse(){

        TaxaMensalResponse response = mapper.dtoToResponse(TAXA_MENSAL_DTO);

        assertThat(response).isNotNull();
        assertThat(TAXA_MENSAL_DTO.getId()).isEqualTo(response.getId());
        assertThat(TAXA_MENSAL_DTO.getMes()).isEqualTo(response.getMes());
        assertThat(TAXA_MENSAL_DTO.getModalidade()).isEqualTo(response.getModalidade());
        assertThat(TAXA_MENSAL_DTO.getPosicao()).isEqualTo(response.getPosicao());
        assertThat(TAXA_MENSAL_DTO.getInstituicaoFinanceira()).isEqualTo(response.getInstituicaoFinanceira());
        assertThat(TAXA_MENSAL_DTO.getTaxaJurosAoMes()).isEqualTo(response.getTaxaJurosAoMes());
        assertThat(TAXA_MENSAL_DTO.getTaxaJurosAoAno()).isEqualTo(response.getTaxaJurosAoAno());
        assertThat(TAXA_MENSAL_DTO.getCnpj8()).isEqualTo(response.getCnpj8());
        assertThat(TAXA_MENSAL_DTO.getAnoMes()).isEqualTo(response.getAnoMes());

    }

    @Test
    public void deveConverterRequestParaDto(){

        TaxaMensalDTO dto = mapper.requestToDTO(TAXA_MENSAL_REQUEST);

        assertThat(dto).isNotNull();
        assertThat(TAXA_MENSAL_REQUEST.getMes()).isEqualTo(dto.getMes());
        assertThat(TAXA_MENSAL_REQUEST.getModalidade()).isEqualTo(dto.getModalidade());
        assertThat(TAXA_MENSAL_REQUEST.getPosicao()).isEqualTo(dto.getPosicao());
        assertThat(TAXA_MENSAL_REQUEST.getInstituicaoFinanceira()).isEqualTo(dto.getInstituicaoFinanceira());
        assertThat(TAXA_MENSAL_REQUEST.getTaxaJurosAoMes()).isEqualTo(dto.getTaxaJurosAoMes());
        assertThat(TAXA_MENSAL_REQUEST.getTaxaJurosAoAno()).isEqualTo(dto.getTaxaJurosAoAno());
        assertThat(TAXA_MENSAL_REQUEST.getCnpj8()).isEqualTo(dto.getCnpj8());
        assertThat(TAXA_MENSAL_REQUEST.getAnoMes()).isEqualTo(dto.getAnoMes());
    }

    @Test
    public void deveConverterDtoParaEntityNull() {

         TaxaMensalDTO dto = null;

        TaxaMensalEntity entity = mapper.dtoToEntity(dto);

        assertThat(entity).isNull();
    }

    @Test
    public void deveConverterEntityParaDtoNull() {

        TaxaMensalEntity entity = null;

        TaxaMensalDTO dto = mapper.entityToDto(entity);

        assertThat(dto).isNull();
    }

    @Test
    public void deveConverterDtoParaResponseNull() {

        TaxaMensalDTO dto = null;

        TaxaMensalResponse response = mapper.dtoToResponse(dto);

        assertThat(response).isNull();
    }

    @Test
    public void deveConverterRequestParaDtoNull() {

        TaxaMensalRequest request = null;

        TaxaMensalDTO dto = mapper.requestToDTO(request);

        assertThat(request).isNull();
    }

    @Test
    public void deveConverterEntityListParaDtoList() {

        List<TaxaMensalDTO> listDTO = new ArrayList<TaxaMensalDTO>( listTaxaMensal().size());
        for (TaxaMensalEntity taxaMensalEntity : listTaxaMensal()) {
            listDTO.add(mapper.entityToDto(taxaMensalEntity));
        }
    }

    @Test
    public void deveConverterDtoListParaEntityList() {

        List<TaxaMensalEntity> listEntity = new ArrayList<TaxaMensalEntity>( listTaxaMensalDTO().size() );
        for (TaxaMensalDTO dto : listTaxaMensalDTO()) {
            listEntity.add(mapper.dtoToEntity(dto));
        }
    }

    @Test
    public void deveConverterDtoListParaResponseList() {

        List<TaxaMensalResponse> responseList = new ArrayList<TaxaMensalResponse>( listTaxaMensalDTO().size());
        for (TaxaMensalDTO taxaMensalDTO : listTaxaMensalDTO()) {
            responseList.add(mapper.dtoToResponse(taxaMensalDTO));
        }
    }

    @Test
    public void deveConverterRequestListParaDtoList(){
        List<TaxaMensalDTO> listDTO = new ArrayList<TaxaMensalDTO>(listTaxaMensalRequest().size());
        for (TaxaMensalRequest request : listTaxaMensalRequest()) {
            listDTO.add(mapper.requestToDTO(request));
        }
    }

    @Test
    public void deveConverterDtoListParaEntityListNull() {

        List<TaxaMensalDTO> listDto = null;

        List<TaxaMensalEntity> entityList = mapper.dtoListToEntityList(listDto);

        assertThat(listDto).isNull();
    }

    @Test
    public void deveConverterEntityListParaDtoListNull() {

        List<TaxaMensalEntity> listEntity = null;

        List<TaxaMensalDTO> dtoList = mapper.entityListToDtoList(listEntity);

        assertThat(dtoList).isNull();
    }

    @Test
    public void deveConverterDtoListParaResponseListNull() {

        List<TaxaMensalDTO> listDto = null;

        List<TaxaMensalResponse> responseList = mapper.dtoListToResponseList(listDto);

        assertThat(responseList).isNull();
    }

    @Test
    public void deveConverterRequestListParaDtoListNull() {

        List<TaxaMensalRequest> requestList = null;

        List<TaxaMensalDTO> listDto = mapper.requestListToDtoList(requestList);

        assertThat(listDto).isNull();
    }

}

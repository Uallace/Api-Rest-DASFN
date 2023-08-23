package br.com.desafio.mapper;

import br.com.desafio.model.entities.TaxaMensalEntity;
import br.com.desafio.model.response.TaxaMensalResponse;
import br.com.desafio.model.dto.TaxaMensalDTO;
import br.com.desafio.model.request.TaxaMensalRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaxaMensalMapper {

    TaxaMensalDTO entityToDto(TaxaMensalEntity taxaMensalEntity);

    TaxaMensalEntity dtoToEntity(TaxaMensalDTO taxaMensalDTO);

    TaxaMensalDTO requestToDTO(TaxaMensalRequest request);

    TaxaMensalResponse dtoToResponse(TaxaMensalDTO taxaMensalDTO);

    List<TaxaMensalDTO> entityListToDtoList(List<TaxaMensalEntity> taxaMensalEntityList);

    List<TaxaMensalEntity> dtoListToEntityList(List<TaxaMensalDTO> taxaMensalDTOList);

    List<TaxaMensalDTO> requestListToDtoList(List<TaxaMensalRequest> taxaMensalRequestList);

    List<TaxaMensalResponse> dtoListToResponseList(List<TaxaMensalDTO> taxaMensalDTOList);
}

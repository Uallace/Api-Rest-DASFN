package br.com.desafio.mapper;

import br.com.desafio.model.dto.TaxaMensalDTO;
import br.com.desafio.model.entities.TaxaMensalEntity;
import br.com.desafio.model.request.TaxaMensalRequest;
import br.com.desafio.model.response.TaxaMensalResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-28T16:24:26-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Homebrew)"
)
@Component
public class TaxaMensalMapperImpl implements TaxaMensalMapper {

    @Override
    public TaxaMensalDTO entityToDto(TaxaMensalEntity taxaMensalEntity) {
        if ( taxaMensalEntity == null ) {
            return null;
        }

        TaxaMensalDTO.TaxaMensalDTOBuilder taxaMensalDTO = TaxaMensalDTO.builder();

        taxaMensalDTO.id( taxaMensalEntity.getId() );
        taxaMensalDTO.mes( taxaMensalEntity.getMes() );
        taxaMensalDTO.modalidade( taxaMensalEntity.getModalidade() );
        taxaMensalDTO.posicao( taxaMensalEntity.getPosicao() );
        taxaMensalDTO.instituicaoFinanceira( taxaMensalEntity.getInstituicaoFinanceira() );
        taxaMensalDTO.taxaJurosAoMes( taxaMensalEntity.getTaxaJurosAoMes() );
        taxaMensalDTO.taxaJurosAoAno( taxaMensalEntity.getTaxaJurosAoAno() );
        taxaMensalDTO.cnpj8( taxaMensalEntity.getCnpj8() );
        taxaMensalDTO.anoMes( taxaMensalEntity.getAnoMes() );

        return taxaMensalDTO.build();
    }

    @Override
    public TaxaMensalEntity dtoToEntity(TaxaMensalDTO taxaMensalDTO) {
        if ( taxaMensalDTO == null ) {
            return null;
        }

        TaxaMensalEntity.TaxaMensalEntityBuilder taxaMensalEntity = TaxaMensalEntity.builder();

        taxaMensalEntity.id( taxaMensalDTO.getId() );
        taxaMensalEntity.mes( taxaMensalDTO.getMes() );
        taxaMensalEntity.modalidade( taxaMensalDTO.getModalidade() );
        taxaMensalEntity.posicao( taxaMensalDTO.getPosicao() );
        taxaMensalEntity.instituicaoFinanceira( taxaMensalDTO.getInstituicaoFinanceira() );
        taxaMensalEntity.taxaJurosAoMes( taxaMensalDTO.getTaxaJurosAoMes() );
        taxaMensalEntity.taxaJurosAoAno( taxaMensalDTO.getTaxaJurosAoAno() );
        taxaMensalEntity.cnpj8( taxaMensalDTO.getCnpj8() );
        taxaMensalEntity.anoMes( taxaMensalDTO.getAnoMes() );

        return taxaMensalEntity.build();
    }

    @Override
    public TaxaMensalDTO requestToDTO(TaxaMensalRequest request) {
        if ( request == null ) {
            return null;
        }

        TaxaMensalDTO.TaxaMensalDTOBuilder taxaMensalDTO = TaxaMensalDTO.builder();

        taxaMensalDTO.mes( request.getMes() );
        taxaMensalDTO.modalidade( request.getModalidade() );
        taxaMensalDTO.posicao( request.getPosicao() );
        taxaMensalDTO.instituicaoFinanceira( request.getInstituicaoFinanceira() );
        taxaMensalDTO.taxaJurosAoMes( request.getTaxaJurosAoMes() );
        taxaMensalDTO.taxaJurosAoAno( request.getTaxaJurosAoAno() );
        taxaMensalDTO.cnpj8( request.getCnpj8() );
        taxaMensalDTO.anoMes( request.getAnoMes() );

        return taxaMensalDTO.build();
    }

    @Override
    public TaxaMensalResponse dtoToResponse(TaxaMensalDTO taxaMensalDTO) {
        if ( taxaMensalDTO == null ) {
            return null;
        }

        TaxaMensalResponse.TaxaMensalResponseBuilder taxaMensalResponse = TaxaMensalResponse.builder();

        taxaMensalResponse.id( taxaMensalDTO.getId() );
        taxaMensalResponse.mes( taxaMensalDTO.getMes() );
        taxaMensalResponse.modalidade( taxaMensalDTO.getModalidade() );
        taxaMensalResponse.posicao( taxaMensalDTO.getPosicao() );
        taxaMensalResponse.instituicaoFinanceira( taxaMensalDTO.getInstituicaoFinanceira() );
        taxaMensalResponse.taxaJurosAoMes( taxaMensalDTO.getTaxaJurosAoMes() );
        taxaMensalResponse.taxaJurosAoAno( taxaMensalDTO.getTaxaJurosAoAno() );
        taxaMensalResponse.cnpj8( taxaMensalDTO.getCnpj8() );
        taxaMensalResponse.anoMes( taxaMensalDTO.getAnoMes() );

        return taxaMensalResponse.build();
    }

    @Override
    public List<TaxaMensalDTO> entityListToDtoList(List<TaxaMensalEntity> taxaMensalEntityList) {
        if ( taxaMensalEntityList == null ) {
            return null;
        }

        List<TaxaMensalDTO> list = new ArrayList<TaxaMensalDTO>( taxaMensalEntityList.size() );
        for ( TaxaMensalEntity taxaMensalEntity : taxaMensalEntityList ) {
            list.add( entityToDto( taxaMensalEntity ) );
        }

        return list;
    }

    @Override
    public List<TaxaMensalEntity> dtoListToEntityList(List<TaxaMensalDTO> taxaMensalDTOList) {
        if ( taxaMensalDTOList == null ) {
            return null;
        }

        List<TaxaMensalEntity> list = new ArrayList<TaxaMensalEntity>( taxaMensalDTOList.size() );
        for ( TaxaMensalDTO taxaMensalDTO : taxaMensalDTOList ) {
            list.add( dtoToEntity( taxaMensalDTO ) );
        }

        return list;
    }

    @Override
    public List<TaxaMensalDTO> requestListToDtoList(List<TaxaMensalRequest> taxaMensalRequestList) {
        if ( taxaMensalRequestList == null ) {
            return null;
        }

        List<TaxaMensalDTO> list = new ArrayList<TaxaMensalDTO>( taxaMensalRequestList.size() );
        for ( TaxaMensalRequest taxaMensalRequest : taxaMensalRequestList ) {
            list.add( requestToDTO( taxaMensalRequest ) );
        }

        return list;
    }

    @Override
    public List<TaxaMensalResponse> dtoListToResponseList(List<TaxaMensalDTO> taxaMensalDTOList) {
        if ( taxaMensalDTOList == null ) {
            return null;
        }

        List<TaxaMensalResponse> list = new ArrayList<TaxaMensalResponse>( taxaMensalDTOList.size() );
        for ( TaxaMensalDTO taxaMensalDTO : taxaMensalDTOList ) {
            list.add( dtoToResponse( taxaMensalDTO ) );
        }

        return list;
    }
}

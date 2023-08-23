package br.com.desafio.service;

import br.com.desafio.exception.BadRequestException;
import br.com.desafio.mapper.TaxaMensalMapper;
import br.com.desafio.model.dto.TaxaMensalDTO;
import br.com.desafio.model.entities.TaxaMensalEntity;
import br.com.desafio.model.response.TaxaMensalResponse;
import br.com.desafio.repository.TaxaMensalRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaxaMensalService {

    @Autowired
    private TaxaMensalRepository repo;

    private TaxaMensalMapper mapper = Mappers.getMapper(TaxaMensalMapper.class);

    public List<TaxaMensalDTO> saveApi(List<TaxaMensalDTO> dtoList){
        try {

            List<TaxaMensalEntity> taxaMensalEntityList = mapper.dtoListToEntityList(dtoList);
            dtoList = mapper.entityListToDtoList(repo.saveAll(taxaMensalEntityList));
            return dtoList;

        }catch (RuntimeException e){
            throw new BadRequestException("Erro ao salvar dados externos!");
        }
    }

    public List<TaxaMensalDTO> getAllTaxas() {

        List<TaxaMensalDTO> dtoList = mapper.entityListToDtoList(repo.findAll());

        if(dtoList.isEmpty()){
            throw new BadRequestException("A lista esta vazia!");
        }

        return  dtoList;

    }

    public Page<TaxaMensalResponse> getPages(Pageable pageable) {
        Page<TaxaMensalDTO> pagesDTO = repo.findAll(pageable).map(t -> mapper.entityToDto(t));
        return pagesDTO.map(t -> mapper.dtoToResponse(t));
    }

    public TaxaMensalDTO getById(Long id){
        Optional<TaxaMensalDTO> taxaDto = repo.findById(id).map(t -> mapper.entityToDto(t));
        return taxaDto.orElseThrow(() -> new BadRequestException("Taxa Mensal nao encontrada!"));
    }

    public TaxaMensalDTO saveEntity(TaxaMensalDTO taxaMensalDTO) {

        TaxaMensalEntity taxaMensalEntity = mapper.dtoToEntity(taxaMensalDTO);
        taxaMensalDTO = mapper.entityToDto(repo.save(taxaMensalEntity));
        return taxaMensalDTO;

    }
    public void delete(Long id){
        TaxaMensalDTO dto = getById(id);
        repo.deleteById(dto.getId());
    }
    public TaxaMensalDTO update(TaxaMensalDTO taxaMensalDTO, Long id){

        Optional<TaxaMensalEntity> taxaEntity = repo.findById(id);
        if(!taxaEntity.isPresent())
            throw new BadRequestException("Taxa Mensal com o Id " + id + " não cadastrada!");

        TaxaMensalEntity entity = taxaEntity.get();

        BeanUtils.copyProperties(taxaMensalDTO, entity, "id");
        var dto = mapper.entityToDto(repo.save(entity));

         return dto;

    }

    public List<TaxaMensalDTO> getByAnoMes(String anoMes) {

        List<TaxaMensalEntity> entityList = repo.findByanoMes(anoMes);
        List<TaxaMensalDTO> dtoList = mapper.entityListToDtoList(entityList);

        if(dtoList.isEmpty()){
            throw new BadRequestException("Nenhum registro do tipo " + anoMes + " encontrado!!");
        }

        return dtoList;

    }

}

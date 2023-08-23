package br.com.desafio.controller;

import br.com.desafio.client.TaxaMensalClient;
import br.com.desafio.mapper.TaxaMensalMapper;
import br.com.desafio.model.request.TaxaMensalRequest;
import br.com.desafio.model.response.TaxaMensalResponse;
import br.com.desafio.util.constraint.jacoco.ExcludeFromJacocoGenerated;
import br.com.desafio.model.dto.TaxaMensalDTO;
import br.com.desafio.service.TaxaMensalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
@Api(value="Api REST Taxa Mensal")
@CrossOrigin(origins = "*")
public class TaxaMensalController {

    @Autowired
    private TaxaMensalService service;

    @Autowired
    private TaxaMensalClient client;

    private TaxaMensalMapper mapper = Mappers.getMapper(TaxaMensalMapper.class);

    @GetMapping
    @ExcludeFromJacocoGenerated
    @ApiOperation(value = "Retorna lista de taxas da Api externa")
    public List<TaxaMensalResponse> getAllApi() {
        return client.getAllApi().value;
    }

    @PostMapping("/onbording")
    @ApiOperation(value = "Salva lista de taxas da Api externa")
    public ResponseEntity salveApiTaxaMensal(@RequestBody @Valid List<TaxaMensalRequest> requestList){
        List<TaxaMensalDTO> dtoList = mapper.requestListToDtoList(requestList);
        List<TaxaMensalResponse> responseList = mapper.dtoListToResponseList(service.saveApi(dtoList));
        return new ResponseEntity(responseList, HttpStatus.CREATED);
    }

    @GetMapping("/taxas")
    @ApiOperation(value = "Retorna lista de taxas salvas no Banco de dados")
    public ResponseEntity getAllTaxas(){
        List<TaxaMensalResponse> response = mapper.dtoListToResponseList(service.getAllTaxas());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/pages")
    @ApiOperation(value = "Retorna lista de taxas com paginacao")
    public ResponseEntity getPages(Pageable pageable){
        return ResponseEntity.ok(service.getPages(pageable));
    }

    @GetMapping("/anomes/{anoMes}")
    @ApiOperation(value = "Retorna taxas pelo AnoMes")
    public ResponseEntity getByAnoMes(@PathVariable("anoMes") String anoMes){
        List<TaxaMensalResponse> responseList = mapper.dtoListToResponseList(service.getByAnoMes(anoMes));
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/taxa/{id}")
    @ApiOperation(value = "Retorna taxa pelo Id")
    public ResponseEntity findByIdTaxas(@PathVariable("id") Long id){
        TaxaMensalResponse response = mapper.dtoToResponse(service.getById(id));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/salvar")
    @ApiOperation(value = "Salva uma taxa no banco de dados")
    public ResponseEntity save(@RequestBody @Valid TaxaMensalRequest request){
        TaxaMensalDTO dto = mapper.requestToDTO(request);
        TaxaMensalResponse response = mapper.dtoToResponse(service.saveEntity(dto));
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/taxa/{id}")
    @ApiOperation(value = "Deleta taxa pelo Id")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/taxa/{id}")
    @ApiOperation(value = "Atualiza taxa de juros")
    public ResponseEntity put(@RequestBody @Valid TaxaMensalRequest request, @PathVariable("id") Long id){
        TaxaMensalDTO taxaMensalDTO = mapper.requestToDTO(request);
        TaxaMensalResponse response = mapper.dtoToResponse(service.update(taxaMensalDTO,id));
        return ResponseEntity.ok(response);
    }

}

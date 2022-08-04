package com.annuaire.contratservice.controller;
import com.annuaire.contratservice.dto.TypeContratDto;
import com.annuaire.contratservice.model.TypeContrat;
import com.annuaire.contratservice.service.TypeContratService;
import com.annuaire.contratservice.tools.Converter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author karim hmadi

 * @description type contrat controllor

 */
@CrossOrigin(origins = "*")
@Slf4j
@RestController
public class TypeContratController {
    private static final Logger logger = LoggerFactory.getLogger(TypeContratController.class);
    @Autowired
    TypeContratService typecontratService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    Converter converter;
    @CrossOrigin(origins = "*")
    @PostMapping("/contrats/type/add/")
    public ResponseEntity<TypeContratDto> createTypeContrat(@RequestBody TypeContratDto typecontratDto)
    {
        logger.info("Methode createTypeContrat(): before : typecontratDto : {} ", typecontratDto);
        TypeContratDto typectr= converter.typeentityToDto(typecontratService.saveTypeContrat(typecontratDto));
        logger.info("Methode createTypeContrat(): after : typecontratDto : {} ", typectr);
        return new ResponseEntity<>(typectr, HttpStatus.CREATED);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/contrats/type/all")
    public ResponseEntity<List<TypeContratDto>> findAll() {

        List<TypeContrat> listtypecontrat = typecontratService.findAllTypeContrats();
        logger.info("Methode findAll(): after : typecontratDto : {} ", converter.typeentityToDto(listtypecontrat));
        return new ResponseEntity<List<TypeContratDto>>(converter.typeentityToDto(listtypecontrat), HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/contrats/type/{id}")
    public ResponseEntity<TypeContratDto> findTypeContratById(@PathVariable(name = "id") String id) {
        TypeContrat typecontrat= typecontratService.findTypeContratById(id);
        // convert entity to DTO
        TypeContratDto typecontratResponse = converter.typeentityToDto(typecontrat);
        logger.info("Methode findTypeContratById(): after : typecontratResponse : {} ", typecontratResponse);
        return ResponseEntity.ok().body(typecontratResponse);
    }
    @CrossOrigin(origins = "*")
    @PutMapping("/contrats/type/update/{id}")
    public ResponseEntity<TypeContratDto> updateTypeContrat(@PathVariable String id, @RequestBody TypeContratDto TypecontratDto) {
        // convert DTO to Entity
        logger.info("Methode updateTypeContrat(): before : TypecontratDto : {} ", TypecontratDto);
        TypeContrat typecontratRequest = converter.typedtoToEntity(TypecontratDto);
        TypeContrat typecontrat = typecontratService.updateTypeContrat(id, typecontratRequest);
        // entity to DTO
        TypeContratDto typecontratResponse = converter.typeentityToDto(typecontrat);
        logger.info("Methode updateTypeContrat(): after : typecontratResponse : {} ", typecontratResponse);
        return ResponseEntity.ok().body(typecontratResponse);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/contrats/type/delete/{id}")
    public ResponseEntity<Boolean> deleteTypeContrat(@PathVariable String id) {
        Boolean deleted = false;
        if(id!=null)
        {
            deleted = typecontratService.deleteTypeContrat(id);
        }
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
}

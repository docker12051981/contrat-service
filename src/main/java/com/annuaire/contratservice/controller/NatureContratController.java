package com.annuaire.contratservice.controller;

import com.annuaire.contratservice.dto.NatureContratDto;
import com.annuaire.contratservice.model.NatureContrat;
import com.annuaire.contratservice.service.NatureContratService;
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

 * @description nature contrat controllor

 */
@CrossOrigin(origins = "*")
@Slf4j
@RestController
public class NatureContratController {
    private static final Logger logger = LoggerFactory.getLogger(NatureContratController.class);
    @Autowired
    NatureContratService naturecontratService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    Converter converter;
    @CrossOrigin(origins = "*")
    @PostMapping("/contrats/nature/add/")
    public ResponseEntity<NatureContratDto> createNatureContrat(@RequestBody NatureContratDto naturecontratDto)
    {
        logger.info("Methode createNatureContrat(): before : naturecontratDto : {} ", naturecontratDto);
        NatureContratDto naturectr= converter.natureentityToDto(naturecontratService.saveNatureContrat(naturecontratDto));
        logger.info("Methode createNatureContrat(): after : naturecontratDto : {} ", naturectr);
        return new ResponseEntity<>(naturectr, HttpStatus.CREATED);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/contrats/nature/all")
    public ResponseEntity<List<NatureContratDto>> findAll() {

        List<NatureContrat> listnaturecontrat = naturecontratService.findAllNatureContrats();
        logger.info("Methode findAll(): after : naturecontratDto : {} ", converter.natureentityToDto(listnaturecontrat));
        return new ResponseEntity<List<NatureContratDto>>(converter.natureentityToDto(listnaturecontrat), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/contrats/nature/type/{id}")
    public ResponseEntity<List<NatureContratDto>> findByType(@PathVariable(name = "id") String id) {

        List<NatureContrat> listnaturecontrat = naturecontratService.findNatureContratByType(id);
        logger.info("Methode findBytype(): after : naturecontratDto : {} ", converter.natureentityToDto(listnaturecontrat));
        return new ResponseEntity<List<NatureContratDto>>(converter.natureentityToDto(listnaturecontrat), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/contrats/nature/{id}")
    public ResponseEntity<NatureContratDto> findNatureContratById(@PathVariable(name = "id") String id) {
        NatureContrat naturecontrat= naturecontratService.findNatureContratById(id);
        // convert entity to DTO
        NatureContratDto naturecontratResponse = converter.natureentityToDto(naturecontrat);
        logger.info("Methode findNatureContratById(): after : naturecontratResponse : {} ", naturecontratResponse);
        return ResponseEntity.ok().body(naturecontratResponse);
    }
    @CrossOrigin(origins = "*")
    @PutMapping("/contrats/nature/update/{id}")
    public ResponseEntity<NatureContratDto> updateNatureContrat(@PathVariable String id, @RequestBody NatureContratDto NaturecontratDto) {
        // convert DTO to Entity
        logger.info("Methode updateNatureContrat(): before : NaturecontratDto : {} ", NaturecontratDto);
        NatureContrat naturecontratRequest = converter.naturedtoToEntity(NaturecontratDto);
        NatureContrat naturecontrat = naturecontratService.updateNatureContrat(id, naturecontratRequest);
        // entity to DTO
        NatureContratDto naturecontratResponse = converter.natureentityToDto(naturecontrat);
        logger.info("Methode updateNatureContrat(): after : naturecontratResponse : {} ", naturecontratResponse);
        return ResponseEntity.ok().body(naturecontratResponse);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/contrats/nature/delete/{id}")
    public ResponseEntity<Boolean> deleteNatureContrat(@PathVariable String id) {
        Boolean deleted = false;
        if(id!=null)
        {
            deleted = naturecontratService.deleteNatureContrat(id);
        }
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
}

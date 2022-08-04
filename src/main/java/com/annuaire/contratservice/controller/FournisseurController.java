package com.annuaire.contratservice.controller;

import com.annuaire.contratservice.dto.FournisseurDto;
import com.annuaire.contratservice.model.Fournisseur;
import com.annuaire.contratservice.service.FournisseurService;
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
public class FournisseurController {
    private static final Logger logger = LoggerFactory.getLogger(FournisseurController.class);
    @Autowired
    FournisseurService fournisseurService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    Converter converter;
    @CrossOrigin(origins = "*")
    @PostMapping("/contrats/fournisseur/add/")
    public ResponseEntity<FournisseurDto> createFournisseur(@RequestBody FournisseurDto fournisseurDto)
    {
        logger.info("Methode createFournisseur(): before : fournisseurDto : {} ", fournisseurDto);
        FournisseurDto fournisseurctr= converter.fournisseurentityToDto(fournisseurService.saveFournisseur(fournisseurDto));
        logger.info("Methode createFournisseur(): after : fournisseurDto : {} ", fournisseurctr);
        return new ResponseEntity<>(fournisseurctr, HttpStatus.CREATED);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/contrats/fournisseur/all")
    public ResponseEntity<List<FournisseurDto>> findAll() {

        List<Fournisseur> listfournisseur = fournisseurService.findAllFournisseurs();
        logger.info("Methode findAll(): after : fournisseurDto : {} ", converter.fournisseurentityToDto(listfournisseur));
        return new ResponseEntity<List<FournisseurDto>>(converter.fournisseurentityToDto(listfournisseur), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/contrats/fournisseur/organisme")
    public ResponseEntity<List<FournisseurDto>> findByOrganisme(@PathVariable(name = "organisme") String organisme) {
        List<Fournisseur> listfournisseur = fournisseurService.findFournisseurByOrganisme(organisme);
        logger.info("Methode findAllFournisseurByOrganisme(): after : fournisseurDto : {} ", converter.fournisseurentityToDto(listfournisseur));
        return new ResponseEntity<List<FournisseurDto>>(converter.fournisseurentityToDto(listfournisseur), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/contrats/fournisseur/{id}")
    public ResponseEntity<FournisseurDto> findFournisseurById(@PathVariable(name = "id") String id) {
        Fournisseur fournisseur= fournisseurService.findFournisseurById(id);
        // convert entity to DTO
        FournisseurDto fournisseurResponse = converter.fournisseurentityToDto(fournisseur);
        logger.info("Methode findFournisseurById(): after : fournisseurResponse : {} ", fournisseurResponse);
        return ResponseEntity.ok().body(fournisseurResponse);
    }
    @CrossOrigin(origins = "*")
    @PutMapping("/contrats/fournisseur/update/{id}")
    public ResponseEntity<FournisseurDto> updateFournisseur(@PathVariable String id, @RequestBody FournisseurDto FournisseurDto) {
        // convert DTO to Entity
        logger.info("Methode updateFournisseur(): before : FournisseurDto : {} ", FournisseurDto);
        Fournisseur fournisseurRequest = converter.fournisseurdtoToEntity(FournisseurDto);
        Fournisseur fournisseur = fournisseurService.updateFournisseur(id, fournisseurRequest);
        // entity to DTO
        FournisseurDto fournisseurResponse = converter.fournisseurentityToDto(fournisseur);
        logger.info("Methode updateFournisseur(): after : fournisseurResponse : {} ", fournisseurResponse);
        return ResponseEntity.ok().body(fournisseurResponse);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/contrats/fournisseur/delete/{id}")
    public ResponseEntity<Boolean> deleteFournisseur(@PathVariable String id) {
        Boolean deleted = false;
        if(id!=null)
        {
            deleted = fournisseurService.deleteFournisseur(id);
        }
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
}

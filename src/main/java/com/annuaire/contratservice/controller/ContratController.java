package com.annuaire.contratservice.controller;
import com.annuaire.contratservice.dto.ContratDto;
import com.annuaire.contratservice.dto.CustomContratDto;
import com.annuaire.contratservice.model.Contrat;
import com.annuaire.contratservice.model.CustomContrat;
import com.annuaire.contratservice.service.IContratService;
import com.annuaire.contratservice.service.SequenceGenerator;
import com.annuaire.contratservice.tools.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author karim hmadi

 * @description structure controllor

 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/contrats")
public class ContratController {

    private IContratService contratService;
    public ContratController(IContratService contratService) {
        super();
        this.contratService = contratService;
    }


    @Autowired
    private SequenceGenerator service;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    Converter converter;
    @CrossOrigin(origins = "*")
    @PostMapping("/")
    public ResponseEntity<ContratDto> createContrat(@RequestBody ContratDto contratDto)
    {
        ContratDto str= converter.contratentityToDto(contratService.saveContrat(contratDto));
        return new ResponseEntity<>(str, HttpStatus.CREATED);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/fournisseur/{fournisseur}")
    public ResponseEntity<List<ContratDto>> findByFournisseur(@PathVariable String fournisseur)
    {
        List<Contrat> findcontratbyfournisseur= contratService.findContratByFournisseur(fournisseur);
        return new ResponseEntity<List<ContratDto>>(converter.contratentityToDto(findcontratbyfournisseur), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/organisme/{organisme}")
    public ResponseEntity<List<ContratDto>> findContratByOrgId(@PathVariable Integer organisme)
    {
        List<Contrat> findcontratbyorgid= contratService.findContratByOrgId(organisme);
        return new ResponseEntity<List<ContratDto>>(converter.contratentityToDto(findcontratbyorgid), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<ContratDto> getContratById(@PathVariable(name = "id") String id) {
        Contrat contrat= contratService.findContratById(id);
        // convert entity to DTO
        ContratDto contratResponse = converter.contratentityToDto(contrat);
        return ResponseEntity.ok().body(contratResponse);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/key/node/{key}")
    public ResponseEntity<CustomContratDto> getContratByCustom(@PathVariable(name = "key") Integer key) {
        CustomContrat customcontrat= contratService.findContratByCustom(key);
        // convert entity to DTO
        CustomContratDto customcontratResponse = converter.customentityToDto(customcontrat);
        return ResponseEntity.ok().body(customcontratResponse);
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteContrat(@PathVariable String id) {
        Boolean deleted = false;
        if(id!=null)
        {
            deleted = contratService.deleteContrat(id);
        }
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/")
    public ResponseEntity<List<ContratDto>> findAll() {
        List<Contrat> findAll = contratService.findAllContrats();
        return new ResponseEntity<List<ContratDto>>(converter.contratentityToDto(findAll), HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/parent/{id}")
    public ResponseEntity<List<ContratDto>> findAllByparent(@PathVariable String id) {
        List<Contrat> findByParent = contratService.findContratsByParent(id);
        return new ResponseEntity<List<ContratDto>>(converter.contratentityToDto(findByParent), HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/nodeId/{id}")
    public ResponseEntity<ContratDto> getContratByKey(@PathVariable(name = "id") Integer id) {
        Contrat contrat= contratService.findContratByKey(id);
        // convert entity to DTO
        ContratDto contratResponse = converter.contratentityToDto(contrat);
        return ResponseEntity.ok().body(contratResponse);
    }
    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ResponseEntity<ContratDto> updateContrat(@PathVariable String id, @RequestBody ContratDto contratDto) {
        // convert DTO to Entity
        Contrat contratRequest = converter.contratdtoToEntity(contratDto);
        Contrat contrat = contratService.updateContrat(id, contratRequest);
        // entity to DTO
        ContratDto contratResponse = converter.contratentityToDto(contrat);
        return ResponseEntity.ok().body(contratResponse);
    }

/*
    @PostMapping("/saveparams/")
    public Param save(@RequestBody Param param)
    {
        if(!param.getName().isEmpty()) {
            param.setTreeId(service.getSequenceNumber(SEQUENCE_NAME));
            return paramRepository.save(param);
        }
        else
        {
            return param;
        }
    }

    @GetMapping("/params")
    public List<Param> getparams()
    {
        return paramRepository.findAll();
    }
*/
}

